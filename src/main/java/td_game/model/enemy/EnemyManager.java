package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.GameObservable;
import java.util.List;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    private final GameObservable notifier;


    public EnemyManager(List<ABaseEnemy> activeEnemies, GameObservable notifier) {
        this.activeEnemies = activeEnemies;
        this.notifier = notifier;
    }

    public void update() {

        activeEnemies.removeIf(enemy -> {
            enemy.move();
            return (!enemy.isAlive() || enemy.hasReachedEnd());
        });

        notifier.notifyObserver(new MovingObjectUpdateEvent());

    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }

    public List<ABaseEnemy> getActiveEnemies() {
        return activeEnemies;
    }

    public void reset() {
        activeEnemies.clear();
    }
}