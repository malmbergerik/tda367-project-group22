public class Enemy1 extends ABaseEnemy{
    private double health;
    private double speed;
    private double posX;
    private double posY;

    public Enemy1(double health, double speed, double posX, double posY) {
        this.health = health;
        this.speed = speed;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void takeDamage(double amount) {
        health -= amount;
        if (health <= 0) die();
    }

    @Override
    public void move() {
        posX ++;
    }
}