package td_game.model.player;

public class PlayerHealth implements IHealth{
    private int health;
    private boolean isDead;
    public PlayerHealth(int health){
        this.health = health;
        this.isDead = false;
    }
    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int amount) {
        health = health - amount;
        if(health<=0){
            isDead = true;
        }

    }

    public boolean isDead(){
        return isDead;
    }
}
