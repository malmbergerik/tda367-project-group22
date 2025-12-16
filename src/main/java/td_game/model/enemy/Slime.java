package td_game.model.enemy;
import td_game.model.path.Path;

/**
 * Slime enemy class that follows a path and can take damage
 */

public class Slime extends PathFollowingEnemy {
    private static final int ENEMY_WIDTH = 8;
    private static final int ENEMY_HEIGHT = 8;
    private static final String NAME = "Slime";

    public Slime(int health, double speed, Path path, int damageAmount){
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
