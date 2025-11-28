package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.IGameObserver;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.enemy.Skeleton;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;
import td_game.model.path.PathManager;
import td_game.model.path.Path;


import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable,IUpdatable {


    private final GridMap gridMap;
    private int x;
    private int y;
    private List<IGameObserver> observers = new ArrayList<IGameObserver>();
    private IGameState currentState;
    private final PathManager pathManager;
    private Path currentPath;
    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    private EnemyManager enemyManager;

    /*
    Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<Tower> placedTowers = new ArrayList<>();
    private List<Projectiles> activeProjectiles = new ArrayList<>();

     */
    public GameModel(int x, int y, int tileSize){

        this.gridMap = MapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.x = x;
        this.y = y;

        this.currentState = new MenuState(this);
        this.pathManager = new PathManager();
        this.currentPath = pathManager.getPathForMap(gridMap);
        this.enemyManager = new EnemyManager(this);

    }
    public Path getCurrentPath(){
        return currentPath;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GridMap getGridMap(){
        return gridMap;
    }

    public List<ABaseEnemy> getActiveEnemies() {return activeEnemies;}

    public void addEnemy(ABaseEnemy enemy){
        activeEnemies.add(enemy);
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

    public void updateEnemies(){
        enemyManager.update();
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
