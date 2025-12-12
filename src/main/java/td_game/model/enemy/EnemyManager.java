package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.events.PlayerHealthUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.GameObservable;
import td_game.model.player.DamageSystem;
import td_game.model.player.MoneySystem;

import java.util.Iterator;
import java.util.List;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    private final GameObservable notifier;
    private final DamageSystem damageSystem;
    private final MoneySystem moneySystem;

    public EnemyManager(List<ABaseEnemy> activeEnemies, GameObservable notifier, DamageSystem damageSystem, MoneySystem moneySystem) {
        this.activeEnemies = activeEnemies;
        this.notifier = notifier;
        this.damageSystem = damageSystem;
        this.moneySystem = moneySystem;
    }

    public void update() {

        Iterator<ABaseEnemy> iterator = activeEnemies.iterator();

        while(iterator.hasNext()) {

            ABaseEnemy enemy = iterator.next();
            enemy.move();

            if (enemy.hasReachedEnd()) {
                damageSystem.handleEnemyReachedEnd(enemy.getDamageAmount()); // Damage == enemy baseHealth (current health?)
                iterator.remove();

            }
            else if (!enemy.isAlive()) {
                moneySystem.handleEnemyKilled(enemy.getDamageAmount()); // Earn == enemy baseHealth, change later
                iterator.remove();
            }
            notifier.notifyObserver(new MovingObjectUpdateEvent());

        }
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