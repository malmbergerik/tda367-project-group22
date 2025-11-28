package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.IGameObserver;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {


    private final GridMap gridMap;
    private int x;
    private int y;
    private List<IGameObserver> observers = new ArrayList<IGameObserver>();
    private IGameState currentState;

    /*
    Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    private List<Tower> placedTowers = new ArrayList<>();
    private List<Projectiles> activeProjectiles = new ArrayList<>();

     */
    public GameModel(int x, int y, int tileSize){

        this.gridMap = MapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.x = x;
        this.y = y;

        this.currentState = new MenuState(this);


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

    public void updateTile(int row, int col, TileBase tile){
        gridMap.setTile(row,col,tile);
        notifyObserver(GameEventType.TILES_UPDATE);
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
            observer.update(eventType);
        }
    }
}
