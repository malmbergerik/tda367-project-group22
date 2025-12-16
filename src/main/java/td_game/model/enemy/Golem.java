package td_game.model.enemy;

import td_game.model.path.Path;

/**
 * Represents a Golem enemy that follows a path.
 */

public class Golem extends PathFollowingEnemy {
    private static final int ENEMY_WIDTH = 16;
    private static final int ENEMY_HEIGHT = 16;
    private static final String NAME = "Golem";

    public Golem(int health, double speed, Path path, int damageAmount) {
        super(health, speed, path, ENEMY_WIDTH, ENEMY_HEIGHT, damageAmount);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void takeDamage(int amount) {
        health -= amount;
    }
}
