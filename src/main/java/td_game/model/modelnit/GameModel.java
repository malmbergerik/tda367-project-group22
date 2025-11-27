package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.GameObserver;
import td_game.model.GameState;
import td_game.model.map.GridMap;
import td_game.model.map.MapLoader;
import td_game.model.map.TileBase;
import td_game.model.path.Path;
import td_game.model.path.PathManager;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyType;
import td_game.model.towers.Tower;
import td_game.model.projectile.Projectile;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameObservable {


    private final GridMap gridMap;
    private int x;
    private int y;
    private List<GameObserver> observers = new ArrayList<GameObserver>();
    private GameState currentState = GameState.PLAYING;
    private int tickCounter = 0;
    private final PathManager pathManager;
    private Path currentPath;



    //Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<ABaseEnemy> activeEnemies = new ArrayList<>();
    //private List<Tower> placedTowers = new ArrayList<>();
    //private List<Projectiles> activeProjectiles = new ArrayList<>();


    public GameModel(int x, int y, int tileSize){

        this.gridMap = MapLoader.loadMap("levels/lvl1.txt", tileSize);
        this.x = x;
        this.y = y;

        this.pathManager = new PathManager();
        this.currentPath = pathManager.getPathForMap(gridMap);


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

    public List<ABaseEnemy> getActiveEnemies(){
        return activeEnemies;
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
            tickCounter++;

            if (tickCounter == 100) {
                tickCounter = 0;
                spawnEnemies();
            }

            updateEnemies();
            /*
            Här vill vi ha alla tower.update(),
            */
        }
    }

    public void updateEnemies () {
        //för varje enemy i activeEnemies
        for (int i = 0; i < activeEnemies.size(); i++) {
            ABaseEnemy enemy = activeEnemies.get(i);
            enemy.move();
            if (!enemy.isAlive() || enemy.hasReachedEnd()) {
                activeEnemies.remove(i);
                i--;
                // Player.deductLife()
                // Player.awardGold()
            }
            // spawn enemies, move enemies, etc.
            // TODO: Deduct lives if hasReachedEnd() is true
            // TODO: Award gold if !enemy.isAlive() is true
        }
        notifyObserver(GameEventType.MOVING_OBJECTS_UPDATE);

    }

    //one for now*
    public void spawnEnemies() {
        if (currentPath != null && currentPath.length() > 0) {
            ABaseEnemy newEnemy = EnemyFactory.createEnemy(EnemyType.skeleton, 100, 1.0, currentPath);
            activeEnemies.add(newEnemy);
            notifyObserver(GameEventType.MOVING_OBJECTS_UPDATE);
        }
    }

    @Override
    public void registerObserver(GameObserver observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterObserver(GameObserver observer) {
        // Todo
    }

    @Override
    public void notifyObserver(GameEventType eventType) {
        for (GameObserver observer: observers) {
            observer.update(eventType);
        }
    }
}
