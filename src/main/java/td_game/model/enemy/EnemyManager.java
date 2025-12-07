package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.modelnit.GameModel;
import java.util.List;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    public GameModel gameModel;


    public EnemyManager(GameModel gameModel, EnemyFactory enemyFactory) {
        this.gameModel = gameModel;
        this.activeEnemies = gameModel.getActiveEnemies();
    }

    public void update() {

        activeEnemies.removeIf(enemy -> {
            enemy.move();
            return (!enemy.isAlive() || enemy.hasReachedEnd());
        });

        gameModel.notifyObserver(new MovingObjectUpdateEvent());

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