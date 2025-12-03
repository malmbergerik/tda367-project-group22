package td_game.model.enemy;
import td_game.model.path.Path;

public class Slime extends PathFollowingEnemy {
    private static final int ENEMY_WIDTH = 8;
    private static final int ENEMY_HEIGHT = 8;
    private static final String NAME = "Slime";

    public Slime(int health, double speed, Path path){
        super(health, speed, path, ENEMY_WIDTH, ENEMY_HEIGHT);
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
