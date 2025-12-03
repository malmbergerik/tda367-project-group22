package td_game.model.enemy;
import td_game.model.path.Path;

public class Skeleton extends PathFollowingEnemy {
    private static final int ENEMY_WIDTH = 8;
    private static final int ENEMY_HEIGHT = 8;

    public Skeleton(int health, double speed, Path path){
        super(health, speed, path, ENEMY_WIDTH, ENEMY_HEIGHT);
    }


    @Override
    public void takeDamage(int amount) {
        health -= amount;
    }

    public String getName() {
        return "Skeleton";
    }

}
