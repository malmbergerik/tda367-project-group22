package td_game.model.projectile.pierceStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;

public interface IPierceStrategy {
    void onHitEnemy(ABaseEnemy enemy);
    boolean hasPierceLeft();

}
