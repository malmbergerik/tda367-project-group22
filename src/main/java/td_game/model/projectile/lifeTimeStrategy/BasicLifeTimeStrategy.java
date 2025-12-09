package td_game.model.projectile.lifeTimeStrategy;

import td_game.model.projectile.Projectile;

public class BasicLifeTimeStrategy implements ILifeTimeStrategy{

    int timeAliveTick;

    public BasicLifeTimeStrategy(int timeAliveTick)
    {
        this.timeAliveTick = timeAliveTick;
    }
    @Override
    public void updateLifetime() {
        this.timeAliveTick -= 1;
    }

    @Override
    public boolean isAlive(Projectile p) {
        if ((this.timeAliveTick <= 0) || p.getPierce() <= 0)
        {
            return false;
        }
        else return true;
    }
}
