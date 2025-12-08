package td_game.model.modelnit;

import java.util.ArrayList;
import java.util.List;
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

public class GameModel implements GameObservable, IUpdatable, IPlayerObserver {

    private final GridMap gridMap;
    private IGameState currentState;
    private final PathManager pathManager;
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

    // New
    private WaveManager waveManager;

    private List<IGameObserver> observers = new ArrayList<>();
    private ATower[][] placedTowerGrid;

    //Systems
    private DamageSystem damageSystem;
    private MoneySystem moneySystem;

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

        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Slime",    path -> new Slime(10, 0.15, path,1));
        enemyFactory.registerFactory("Skeleton", path -> new Skeleton(2, 0.3, path,2));
        enemyFactory.registerFactory("Golem",    path -> new Golem(100, 0.2, path,5));
        enemyFactory.registerFactory("Bat",      path -> new Bat(1, 0.5, path,3));
        enemyFactory.registerFactory("BabyOrc",  path -> new BabyOrc(20, 0.25, path,8));

        this.enemyManager = new EnemyManager(this.activeEnemies, this, damageSystem, moneySystem);

        WaveLoader waveLoader = new WaveLoader();
        Queue<Wave> waves = waveLoader.loadWaves("waves/waves.txt");

        this.waveManager = new WaveManager(enemyManager, enemyFactory, waves, currentPath);

        // Start first wave
        this.waveManager.startNextWave();

        this.towerManager = new TowerManager(this, moneySystem);
        this.projectileManager = new ProjectileManager(this);
        this.towerFactory = new TowerFactory(projectileManager);

        placedTowerGrid = new ATower[gridMap.getRow()][gridMap.getCol()];
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

    public void addProjectile(Projectile projectile) {
        activeProjectiles.add(projectile);
    }

    public boolean addTower(ATower tower) {
        return towerManager.addTower(tower);
    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }


    public void updateEnemies() {

        enemyManager.update();
        waveManager.update();


        if (waveManager.isWaveComplete() && activeEnemies.isEmpty()) {
            waveManager.startNextWave();
            notifyObserver(new WaveUpdateEvent());
        }
    }

    public void updateProjectile() {
        projectileManager.update();
    }

    public void updateTower() {
        towerManager.update();
    }

    @Override
    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void updateTile(int row, int col, Tile tile) {
        gridMap.setTile(row, col, tile);
        notifyObserver(new TileUpdateEvent());
    }

    public Boolean gridOccupied(int row, int col) {
        if (placedTowerGrid[row][col] != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean canBePlaced(int row, int col, String tower) {
        if (!towerFactory.isValidTower(tower)) return false;

        int tileSize = gridMap.getTileSize();

        ATower t = towerFactory.createTower(tower, col * tileSize, row * tileSize);

        if (t == null) return false;
        return t.canBePlaced(gridMap.getTile(row, col));
    }

    public void placeTower(int row, int col, String tower) {
        if (gridOccupied(row, col)) {
            System.out.println("A tower is already placed here!");
            return;
        }

        if (!canBePlaced(row, col, tower)) {
            System.out.println("Tower cant be placed here");
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

    @Override
    public void unregisterObserver(IGameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(IGameEvent event) {
        for (IGameObserver observer : observers) {
            event.dispatch(observer);
        }
    }

    @Override
    public void onHealthChanged(int newHealth) {
        notifyObserver(new PlayerHealthUpdateEvent());
    }

    @Override
    public void onPlayerDeath() {
        System.out.println("You Lose!");

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

    public void togglePause(){
        if(currentState instanceof PlayingState){
            setGameState(new PausedState());
        }
        else if(currentState instanceof PausedState){
            setGameState(new PlayingState(this));
        }
    }
}