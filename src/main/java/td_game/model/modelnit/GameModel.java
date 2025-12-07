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

import td_game.model.events.IGameEvent;
import td_game.model.events.IGameObserver;
import td_game.model.events.TileUpdateEvent;
import td_game.model.events.TowersUpdateEvent;

import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.Tile;
import td_game.model.map.TileFactory;

import td_game.model.path.Path;
import td_game.model.path.PathManager;

import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;

import td_game.model.towers.ATower;
import td_game.model.towers.TowerManager;
import td_game.model.towers.factory.TowerFactory;

import td_game.model.waves.Wave;
import td_game.model.waves.WaveLoader;
import td_game.model.waves.WaveManager;

public class GameModel implements GameObservable, IUpdatable {

    private final GridMap gridMap;
    private IGameState currentState;
    private final PathManager pathManager;
    private Path currentPath;

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

    public GameModel(int tileSize) {
        TileFactory tileFactory = new TileFactory();
        MapLoader mapLoader = new MapLoader(tileFactory);
        this.gridMap = mapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.currentState = new MenuState(this);
        this.pathManager = new PathManager();
        this.currentPath = pathManager.getPathForMap(gridMap);

        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Slime",    path -> new Slime(10, 0.3, path));
        enemyFactory.registerFactory("Skeleton", path -> new Skeleton(2, 0.5, path));
        enemyFactory.registerFactory("Golem",    path -> new Golem(100, 0.2, path));
        enemyFactory.registerFactory("Bat",      path -> new Bat(1, 0.8, path));

        this.enemyManager = new EnemyManager(this, enemyFactory);

        WaveLoader waveLoader = new WaveLoader();
        Queue<Wave> waves = waveLoader.loadWaves("waves/waves.txt");

        this.waveManager = new WaveManager(enemyManager, enemyFactory, waves, currentPath);

        // Start first wave
        this.waveManager.startNextWave();

        this.towerManager = new TowerManager(this);
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

    public void addTower(ATower tower) {
        activeTowers.add(tower);
    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }

    // ÄNDRAD METOD: Hanterar nu både fiender och våg-logik
    public void updateEnemies() {
        // Uppdatera fiendernas rörelser
        enemyManager.update();

        // Uppdatera våg-systemet (spawna nya fiender vid behov)
        waveManager.update();

        // Kolla om vågen är klar och om vi ska starta nästa
        if (waveManager.isWaveComplete() && activeEnemies.isEmpty()) {
            waveManager.startNextWave();
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
            placedTowerGrid[row][col] = t;
            addTower(t);
            notifyObserver(new TowersUpdateEvent());
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
}