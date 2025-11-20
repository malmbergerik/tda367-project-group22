package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.GameObserver;
import td_game.model.GameState;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {


    private final GridMap gridMap;
    private int x;
    private int y;
    private List<GameObserver> observers = new ArrayList<GameObserver>();
    private GameState currentState = GameState.MENU;

    /*
    Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<Enemy> activeEnemies = new ArrayList<>();
    private List<Tower> placedTowers = new ArrayList<>();
    private List<Projectiles> activeProjectiles = new ArrayList<>();

     */
    public GameModel(int x, int y, int tileSize){

        this.gridMap = MapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.x = x;
        this.y = y;

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

    public void setGameState(GameState gameState) {
        // TODO
    }

    public void gameTick() {
        if (currentState == GameState.PLAYING) {
            // TODO

           /*
            Här vill vi ha alla tower.update(), enemies.update()...

            */
            //notifyObserver();
        }
    }

    @Override
    public void registerObserver(GameObserver observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(GameEventType eventType) {
        for (GameObserver observer: observers) {
            observer.update(eventType);
        }
    }
}
