package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyManager;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;
import td_game.model.IGameObserver;
import td_game.model.map.TileFactory;
import td_game.model.path.Path;
import td_game.model.path.PathManager;
import td_game.model.projectile.IProjectileFactory;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.towers.Tower;
import td_game.model.enemy.EnemyFactory;

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
    private List<IGameObserver> observers = new ArrayList<>();
    private EnemyManager enemyManager;


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
        this.enemyManager = new EnemyManager(this, enemyFactory);

        placedTowerGrid = new Tower[gridMap.getRow()][gridMap.getCol()];
    }

    public Path getCurrentPath(){
        return currentPath;
    }

    public GridMap getGridMap(){
        return gridMap;
    }

    public List<ABaseEnemy> getActiveEnemies() {return activeEnemies;}

    public void addEnemy(ABaseEnemy enemy){
        activeEnemies.add(enemy);
    }
    public void updateEnemies(){
        enemyManager.update();
    }
    public void update(){
        if (currentState != null) {
            currentState.update();
        }
    }

    public void updateTile(int row, int col, TileBase tile){
        gridMap.setTile(row,col,tile);
        notifyObserver(GameEventType.TILES_UPDATE);
    }

    public Boolean gridOccupied(int row, int col){
        if(placedTowerGrid[row][col] != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void placeTower(int row, int col, String tower){
        if(gridOccupied(row,col)){
            System.out.println("A tower is already placed here!");
            return;
        }

        int tileSize = gridMap.getTileSize();
        ProjectileFactory projectileFactory = new ProjectileFactory(10,10,10,10,10,10);
        placedTowerGrid[row][col] = new Tower(1,col*tileSize,row*tileSize,10,10,projectileFactory);
        notifyObserver(GameEventType.TOWER_UPDATE);
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
    public void notifyObserver(GameEventType eventType) {
        for (IGameObserver observer: observers) {
            observer.onGameEvent(eventType);
        }
    }
}
