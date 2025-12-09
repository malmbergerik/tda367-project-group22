package td_game.model.projectile.damageTypeStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;

public class BasicDamageTypeStrategy implements IDamagetypeStrategy{
    private int damage;
    public BasicDamageTypeStrategy(int damage){
        this.damage =damage;
    }

    @Override
    public void onHit(ABaseEnemy enemy) {
        enemy.takeDamage(this.damage);
    }

    @Override
    public int getDamage() {
        return this.damage;
    }
}
