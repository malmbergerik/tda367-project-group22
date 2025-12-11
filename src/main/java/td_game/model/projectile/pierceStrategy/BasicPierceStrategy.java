package td_game.model.projectile.pierceStrategy;

import td_game.model.enemy.ABaseEnemy;

public class BasicPierceStrategy implements IPierceStrategy{

    private int pierceLeft;

    public BasicPierceStrategy(int pierce) {
        this.pierceLeft = pierce;
    }

    public BasicPierceStrategy copy() {
        return new BasicPierceStrategy(this.pierceLeft);
    }

    @Override
    public boolean hasPierceLeft() {
        if (pierceLeft >=1)
        {
            return true;
        }
        else return false;
    }

    public void reducePierce(){ pierceLeft -= 1;}
}
