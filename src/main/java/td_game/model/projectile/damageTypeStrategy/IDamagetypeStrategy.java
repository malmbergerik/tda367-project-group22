package td_game.model.projectile.damageTypeStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;

public interface IDamagetypeStrategy {
    void onHit( ABaseEnemy enemy);
    int getDamage();
}
