package td_game.model.enemy;

import td_game.model.path.Path;

public class Bat extends PathFollowingEnemy {

    private static final int ENEMY_WIDTH = 6;
    private static final int ENEMY_HEIGHT = 6;
    private static final String NAME = "Bat"; //

    public Bat(int health, double speed, Path path){
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