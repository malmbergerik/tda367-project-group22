package td_game.model.enemy;

import td_game.model.path.Path;

public class BabyOrc extends PathFollowingEnemy {
    private static final int ENEMY_WIDTH = 12;
    private static final int ENEMY_HEIGHT = 12;
    private static final String NAME = "BabyOrc";

    public BabyOrc(int health, double speed, Path path, int damageAmount) {
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
