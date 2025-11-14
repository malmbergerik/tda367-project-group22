package td_game.model.modelnit;

import td_game.model.GameState;

import java.util.ArrayList;

public class GameModel implements GameObservable {
    private List<GameObservers> observers = new ArrayList<>();
    private GameState currentState = GameState.Menu;

    /*
    Här vill vi ha listor för torn, enemies, pengar, spelaren...

    private List<Enemy> activeEnemies = new ArrayList<>();
    private List<Tower> placedTowers = new ArrayList<>();
    private List<Projectiles> activeProjectiles = new ArrayList<>();

     */

    public void setGameState(GameState gameState) {
        // TODO
    }

    public void gameTick() {
        if (currentState == GameState.PLAYING) {
            // TODO

           /*
            Här vill vi ha alla tower.update(), enemies.update()...

            */
            notifyObserver();
        }
    }

    @Override
    public void registerObserver(GameObserver observer) {

    }

    @Override
    public void unregisterObserver(GameObserver observer) {

    }

    @Override
    public void notifyObserver() {
        for (GameObservable observer: observers) {
            observer.update();
        }
    }
}
