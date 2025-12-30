package td_game.model.modelnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Bat;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.enemy.Golem;
import td_game.model.enemy.Skeleton;
import td_game.model.enemy.Slime;
import td_game.model.enemy.BabyOrc;

import td_game.model.events.*;

import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.Tile;
import td_game.model.map.TileFactory;

import td_game.model.path.Path;
import td_game.model.path.PathManager;

import td_game.model.player.*;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;

import td_game.model.towers.ATower;
import td_game.model.towers.TowerManager;
import td_game.model.towers.factory.TowerFactory;

import td_game.model.waves.Wave;
import td_game.model.waves.WaveLoader;
import td_game.model.waves.WaveManager;

/**
 * The GameModel class acts as the central hub for the game's logic and state management.
 * It aggregates various sub-systems (enemies, towers, map, player) and coordinates their interactions.
 * Implements the Observer pattern to notify the view of state changes.
 *
 * DISCLAIMER: This class acts as the single source of truth for the game state.
 * Modifications here propagate to all registered observers.
 */
public class GameModel implements GameObservable, IUpdatable, IPlayerObserver {

    private GridMap gridMap;
    private IGameState currentState;
    private PathManager pathManager;
    private Path currentPath;

    //Player
    private Player player;
    // Entities
    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    private List<Projectile> activeProjectiles = new ArrayList<>();
    private List<ATower> activeTowers = new ArrayList<>();

    // Managers
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private TowerFactory towerFactory;
    private EnemyFactory enemyFactory;
    // New
    private WaveManager waveManager;

    //Observers for game
    private List<IGameObserver> observers = new ArrayList<>();
    private List<IGameStateObserver> stateObservers = new ArrayList<>();
    private List<IResetListener> resetListeners = new ArrayList<>();


    private ATower[][] placedTowerGrid;

    //Systems
    private DamageSystem damageSystem;
    private MoneySystem moneySystem;

    /**
     * Constructs the GameModel and initializes all game subsystems.
     * Loads the default map, configures factories, and sets up the initial game state.
     *
     * @param tileSize The size of a single tile in pixels (used for map loading).
     */
    public GameModel(int tileSize) {
        TileFactory tileFactory = new TileFactory();
        MapLoader mapLoader = new MapLoader(tileFactory);
        this.gridMap = mapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.currentState = new MenuState(this);
        this.pathManager = new PathManager();
        this.currentPath = pathManager.getPathForMap(gridMap);

        this.player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        player.addObserver(this);
        this.damageSystem = new DamageSystem(player);
        this.moneySystem = new MoneySystem(player);

        this.enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Slime",    path -> new Slime(80, 0.2, path,1));
        enemyFactory.registerFactory("Skeleton", path -> new Skeleton(100, 0.4, path,3));
        enemyFactory.registerFactory("Golem",    path -> new Golem(10000, 0.1, path,100));
        enemyFactory.registerFactory("Bat",      path -> new Bat(60, 0.7, path,2));
        enemyFactory.registerFactory("BabyOrc",  path -> new BabyOrc(250, 0.6, path,4));

        this.enemyManager = new EnemyManager(this.activeEnemies, this, damageSystem, moneySystem);

        WaveLoader waveLoader = new WaveLoader();
        Queue<Wave> waves = waveLoader.loadWaves("waves/waves.txt");

        this.waveManager = new WaveManager(enemyManager, enemyFactory, waves, currentPath);

        // Start first wave

        this.towerManager = new TowerManager(this, moneySystem);
        this.projectileManager = new ProjectileManager(this);
        this.towerFactory = new TowerFactory(projectileManager);

        placedTowerGrid = new ATower[gridMap.getRow()][gridMap.getCol()];
    }

    /**
     * Resets the entire game state to the initial configuration.
     * Re-initializes managers, clears entities, reloads waves, and resets player stats.
     * Triggers notifications to update the view.
     */
    public void resetGame(){
        activeEnemies.clear();
        activeProjectiles.clear();
        activeTowers.clear();
        player.removeObserver(this);
        this.player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        player.addObserver(this);
        damageSystem = new DamageSystem(player);
        moneySystem = new MoneySystem(player);

        enemyManager = new EnemyManager(this.activeEnemies, this, damageSystem, moneySystem);
        WaveLoader waveLoader = new WaveLoader();
        Queue<Wave> waves = waveLoader.loadWaves("waves/waves.txt");

        waveManager = new WaveManager(enemyManager, enemyFactory, waves, currentPath);
        notifyObserver(new WaveUpdateEvent());

        towerManager = new TowerManager(this, moneySystem);
        this.projectileManager = new ProjectileManager(this);
        this.towerFactory = new TowerFactory(projectileManager);

        placedTowerGrid = new ATower[gridMap.getRow()][gridMap.getCol()];
        onHealthChanged(100);
        onMoneyChanged();
        notifyObserver(new TowersUpdateEvent());
        notifyObserver(new MovingObjectUpdateEvent());
        notifyObserver(new ProjectileUpdateEvent());

        for (IResetListener listener : resetListeners) {
            listener.onReset();
        }
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public GridMap getGridMap() {
        return gridMap;
    }

    public List<ABaseEnemy> getActiveEnemies() {
        return activeEnemies;
    }

    public List<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }

    public List<ATower> getActiveTowers() {
        return activeTowers;
    }

    public Map<String, Integer> getTowerPrices() {
        return towerFactory.getTowerPrices();
    }

    public void addProjectile(Projectile projectile) {
        activeProjectiles.add(projectile);
    }

    public boolean addTower(ATower tower) {
        return towerManager.addTower(tower);
    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }


    /**
     * Updates the state of all active enemies and the current wave.
     * Checks if the wave is complete or if the game has been won.
     */
    public void updateEnemies() {

        enemyManager.update();
        waveManager.update();


        if (waveManager.isWaveComplete() && activeEnemies.isEmpty()) {
            waveManager.waveFinished();
            notifyObserver(new WaveUpdateEvent());
        }

        if (waveManager.isAllWavesCompleted()) {
            notifyGameWon();
        }
    }

    /**
     * Initiates the next wave if no wave is currently active.
     */
    public void startNextWave() {
        if (!waveManager.isWaveActive()) {
            waveManager.startNextWave();
            notifyObserver(new WaveUpdateEvent());
        }
    }

    public boolean isWaveActive() {
        return waveManager.isWaveActive();
    }

    public void updateProjectile() {
        projectileManager.update();
    }

    public void updateTower() {
        towerManager.update();
    }

    /**
     * Updates the game model logic.
     * Delegates the update to the current game state (e.g., Playing, Menu).
     */
    @Override
    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    /**
     * Updates a specific tile in the grid map.
     *
     * @param row  The row index.
     * @param col  The column index.
     * @param tile The new tile object.
     */
    public void updateTile(int row, int col, Tile tile) {
        gridMap.setTile(row, col, tile);
        notifyObserver(new TileUpdateEvent());
    }

    /**
     * Checks if a specific grid cell is occupied by a tower.
     *
     * @param row The row index.
     * @param col The column index.
     * @return true if a tower exists at the location, false otherwise.
     */
    public Boolean gridOccupied(int row, int col) {
        if (placedTowerGrid[row][col] != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public ATower getTower(int row, int col){
        return placedTowerGrid[row][col];
    }

    /**
     * Sells the tower at the specified grid location.
     * Removes the tower from the manager and updates the player's money.
     *
     * @param row The row index.
     * @param col The column index.
     */
    public void sellTower(int row, int col){
        ATower tower = placedTowerGrid[row][col];
        towerManager.removeTower(tower);
        placedTowerGrid[row][col] = null;
        notifyObserver(new TowersUpdateEvent());
    }

    /**
     * Checks if a tower of a specific type can be placed at the given location.
     *
     * @param row    The row index.
     * @param col    The column index.
     * @param tower  The type/name of the tower.
     * @return true if placement is valid (valid type, enough money, valid terrain), false otherwise.
     */
    public Boolean canBePlaced(int row, int col, String tower) {
        if (!towerFactory.isValidTower(tower)) return false;

        int tileSize = gridMap.getTileSize();

        ATower t = towerFactory.createTower(tower, col * tileSize, row * tileSize);

        if (t == null) return false;
        return t.canBePlaced(gridMap.getTile(row, col));
    }

    /**
     * Attempts to place a tower at the specified grid coordinates.
     * verifies validity (occupation and rules) before updating the game state.
     *
     * @param row    The row index.
     * @param col    The column index.
     * @param tower  The type/name of the tower to place.
     */
    public void placeTower(int row, int col, String tower) {
        if (gridOccupied(row, col)) {
            return;
        }

        if (!canBePlaced(row, col, tower)) {
            return;
        }

        int tileSize = gridMap.getTileSize();

        ATower t = towerFactory.createTower(tower, col * tileSize, row * tileSize);

        if (t != null) {
            if (addTower(t))
            {
                placedTowerGrid[row][col] = t;
                notifyObserver(new TowersUpdateEvent());
            }
        }
    }

    public ATower[][] getPlacedTowerGrid() {
        return placedTowerGrid;
    }

    /**
     * Transitions the game to a new state.
     * Handles exit logic for the previous state and entry logic for the new state.
     *
     * @param newState The new IGameState to transition to.
     */
    public void setGameState(IGameState newState) {
        if (currentState != null) {
            currentState.exitState();
        }
        currentState = newState;
        if (currentState != null) {
            currentState.enterState();
        }
    }

    @Override
    public void registerObserver(IGameObserver observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }
    public void registerStateObserver(IGameStateObserver observer) {
        if (!stateObservers.contains(observer))
            stateObservers.add(observer);
    }

    public void addResetListener(IResetListener listener) {
        resetListeners.add(listener);
    }

    @Override
    public void unregisterObserver(IGameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Unregisters all observers and listeners.
     * Useful when shutting down the game or performing a hard reset.
     */
    public void unregisterAllObserver(){
        observers.clear();
        stateObservers.clear();
        player.removeObserver(this);
    }
    @Override
    public void notifyObserver(IGameEvent event) {
        for (IGameObserver observer : observers) {
            event.dispatch(observer);
        }
    }

    /**
     * Notifies all state observers that the game has been won.
     */
    public void notifyGameWon() {
        setGameState(new WonState());
        for (IGameStateObserver observer : stateObservers) {
            observer.onGameWon();
        }
    }


    @Override
    public void onHealthChanged(int health) {
        notifyObserver(new PlayerHealthUpdateEvent());
    }

    @Override
    public void onPlayerDeath() {
        setGameState(new LostState());
        for (IGameStateObserver observer : stateObservers) {
            observer.onGameLost();
        }
    }

    public void onMoneyChanged(){
        notifyObserver(new PlayerMoneyUpdateEvent());
    }

    public int getHealth() {
        return player.getHealth();
    }

    public int getMoney() {
        return player.getMoney();
    }

    public int getCurrentWave() {
        return waveManager.getCurrentWave();
    }

    /**
     * Toggles the game state between Playing and Paused.
     * Notifies state observers of the change.
     */
    public void togglePause(){
        if(currentState instanceof PlayingState){
            setGameState(new PausedState());

            for (IGameStateObserver observer : stateObservers) {
                observer.onGamePause();
            }

        }
        else if(currentState instanceof PausedState){
            setGameState(new PlayingState(this));
            for (IGameStateObserver observer : stateObservers) {
                observer.onGameUnPause();
            }
        }
    }

    /**
     * Retrieves the range of a specific tower type.
     * Creates a temporary tower instance to access its properties.
     *
     * @param towerName The name of the tower.
     * @return The range of the tower in pixels.
     */
    public int getTowerRangeByName(String towerName) {
        ATower tempTower = towerFactory.createTower(towerName, 0, 0);
        return tempTower.getRange();
    }
}