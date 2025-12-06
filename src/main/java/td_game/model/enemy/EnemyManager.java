package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.modelnit.GameModel;
import java.util.List;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    public GameModel gameModel;
    // ÄNDRING: WaveManager är borttagen härifrån

    public EnemyManager(GameModel gameModel, EnemyFactory enemyFactory) {
        this.gameModel = gameModel;
        this.activeEnemies = gameModel.getActiveEnemies();
        // ÄNDRING: Vi startar inte vågen här längre. GameModel styr det.
    }

    public void update() {
        // ÄNDRING: WaveManager.update() anropas inte här längre

        activeEnemies.removeIf(enemy -> {
            enemy.move();
            return (!enemy.isAlive() || enemy.hasReachedEnd());
        });

        gameModel.notifyObserver(new MovingObjectUpdateEvent());

        // ÄNDRING: Logiken för att kolla "isWaveComplete" flyttas upp till GameModel
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