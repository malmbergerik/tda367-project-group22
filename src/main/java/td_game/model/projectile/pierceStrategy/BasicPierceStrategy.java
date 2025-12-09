package td_game.model.projectile.pierceStrategy;

import td_game.model.enemy.ABaseEnemy;

public class BasicPierceStrategy implements IPierceStrategy{

    private int pierceLeft;

    public BasicPierceStrategy(int pierce) {
        this.pierceLeft = pierce;
    }

    @Override
    public boolean hasPierceLeft() {
        if (pierceLeft >=1)
        {
            return true;
        }
        else return false;
    }

    @Override
    public void onHitEnemy( ABaseEnemy enemy) {
        pierceLeft -= 1;
    }
}
