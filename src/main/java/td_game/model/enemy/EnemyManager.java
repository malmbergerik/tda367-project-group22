package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.events.PlayerHealthUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.GameObservable;
import td_game.model.player.DamageSystem;
import td_game.model.player.MoneySystem;

import java.util.Iterator;
import java.util.List;

/**
 * Manages the lifecycle and updates of all active enemies in the game.
 * Handles movement, death logic, player damage, and rewards (money) per tick.
 */
public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    private final GameObservable notifier;
    private final DamageSystem damageSystem;
    private final MoneySystem moneySystem;

    /**
     * Constructs the EnemyManager with required dependencies.
     *
     * @param activeEnemies The shared list where active enemy objects are stored.
     * @param notifier      The system to notify the View when enemies move or change.
     * @param damageSystem  The system to handle deducting player health.
     * @param moneySystem   The system to handle awarding money to the player.
     */
    public EnemyManager(List<ABaseEnemy> activeEnemies, GameObservable notifier, DamageSystem damageSystem, MoneySystem moneySystem) {
        this.activeEnemies = activeEnemies;
        this.notifier = notifier;
        this.damageSystem = damageSystem;
        this.moneySystem = moneySystem;
    }

    /**
     * Updates the state of all active enemies.
     * Moves enemies, removes them if they die or reach the end, and triggers
     * appropriate game events (damage to player or money reward).
     */
    public void update() {

        Iterator<ABaseEnemy> iterator = activeEnemies.iterator();

        while(iterator.hasNext()) {

            ABaseEnemy enemy = iterator.next();
            enemy.move();

            if (enemy.hasReachedEnd()) {
                // Inflict damage if enemy breaches defenses
                damageSystem.handleEnemyReachedEnd(enemy.getDamageAmount());
                iterator.remove();

            }
            else if (!enemy.isAlive()) {
                // Award money if enemy is killed by player
                moneySystem.handleEnemyKilled(enemy.getDamageAmount());
                iterator.remove();
            }
            notifier.notifyObserver(new MovingObjectUpdateEvent());

        }
    }

    /**
     * Adds a new enemy instance to the active game loop.
     *
     * @param enemy The initialized enemy object to add.
     */
    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }

    public List<ABaseEnemy> getActiveEnemies() {
        return activeEnemies;
    }

    /**
     * Removes all active enemies from the game immediately.
     * Typically used when restarting the game.
     */
    public void reset() {
        activeEnemies.clear();
    }
}