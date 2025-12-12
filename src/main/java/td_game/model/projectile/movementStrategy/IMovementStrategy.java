package td_game.model.projectile.movementStrategy;

import td_game.model.projectile.Projectile;

public interface IMovementStrategy {

    BasicMovementStrategy copy();
    void move(Projectile p);
}
