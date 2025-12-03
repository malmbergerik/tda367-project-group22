package td_game.model.modelnit;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyManager;
import td_game.model.events.IGameEvent;
import td_game.model.events.TileUpdateEvent;
import td_game.model.events.TowersUpdateEvent;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;
import td_game.model.events.IGameObserver;
import td_game.model.map.TileFactory;
import td_game.model.path.Path;
import td_game.model.path.PathManager;

import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.towers.Tower;
import td_game.model.towers.TowerManager;
import td_game.model.projectile.ProjectileManager;

import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.Skeleton;
import td_game.model.enemy.Slime;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameModel implements GameObservable,IUpdatable {


    private final GridMap gridMap;
    private int x;
    private int y;
    private IGameState currentState;
    private final PathManager pathManager;
    private Path currentPath;
    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    private List<Projectile> activeProjectiles = new ArrayList<>();
    private List<Tower> activeTowers = new ArrayList<>();
    private List<IGameObserver> observers = new ArrayList<>();
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;

    private Tower[][] placedTowerGrid;
    /*
    Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    private List<Tower> placedTowers = new ArrayList<>();
    private List<Projectiles> activeProjectiles = new ArrayList<>();

     */
    public GameModel( int tileSize){
        TileFactory tileFactory = new TileFactory();
        MapLoader mapLoader = new MapLoader(tileFactory);
        this.gridMap = mapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.currentState = new MenuState(this);
        this.pathManager = new PathManager();
        this.currentPath = pathManager.getPathForMap(gridMap);

        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Slime", Slime::new);
        enemyFactory.registerFactory("Skeleton", Skeleton::new);
        this.enemyManager = new EnemyManager(this, enemyFactory);

        this.towerManager = new TowerManager( this);
        this.projectileManager = new ProjectileManager(this);
        placedTowerGrid = new Tower[gridMap.getRow()][gridMap.getCol()];
    }

    public Path getCurrentPath(){
        return currentPath;
    }

    public GridMap getGridMap(){
        return gridMap;
    }

    public List<ABaseEnemy> getActiveEnemies() {return activeEnemies;}

    public List<Projectile> getActiveProjectiles() {return activeProjectiles;}
    public List<Tower> getActiveTowers(){ return activeTowers;}

    public void addProjectile(Projectile projectile){
        activeProjectiles.add(projectile);
    }
    public void addTower(Tower tower){
        activeTowers.add(tower);
    }

    public void addEnemy(ABaseEnemy enemy){
        activeEnemies.add(enemy);
    }
    public void updateEnemies(){
        enemyManager.update();
    }

    public void updateProjectile(){
        projectileManager.update();
    }

    public void updateTower(){
        towerManager.update();
    }

    public void update(){
        if (currentState != null) {
            currentState.update();
        }
    }

    public void updateTile(int row, int col, TileBase tile){
        gridMap.setTile(row,col,tile);
        notifyObserver(new TileUpdateEvent());
    }

    public Boolean gridOccupied(int row, int col){
        if(placedTowerGrid[row][col] != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean canBePlaced(int row, int col, String tower){
        //TODO Break this out so it is not dependent on creating new towers in check
        int tileSize = gridMap.getTileSize();
        Tower t = new Tower(320,col*tileSize,row*tileSize,30,1, new ProjectileFactory(1,8,8,1,1,60,true));
        return t.canBePlaced(gridMap.getTile(row,col));
    }

    public void placeTower(int row, int col, String tower){
        if(gridOccupied(row,col)){
            System.out.println("A tower is already placed here!");
            return;
        }

        if(!canBePlaced(row, col, tower)){
            System.out.println("Tower cant be placed here");
            return;
        }

        int tileSize = gridMap.getTileSize();
        Tower t = new Tower(320,col*tileSize,row*tileSize,30,1, new ProjectileFactory(1,8,8,1,1,60,true));
        placedTowerGrid[row][col] = t;
        addTower(t);
        notifyObserver(new TowersUpdateEvent());
    }

    public Tower[][] getPlacedTowerGrid(){
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
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterObserver(IGameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(IGameEvent event) {
        for (IGameObserver observer: observers) {
            event.dispatch(observer);
        }
    }
}
