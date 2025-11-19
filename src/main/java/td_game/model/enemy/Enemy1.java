package td_game.model.enemy;

public class Enemy1 extends ABaseEnemy{

    public Enemy1(double health, double speed, Path path){
        this.health = health;
        this.speed = speed;
        this.path = path;
    }

    @Override
    public double getX() {
        return posX;
    }

    @Override
    public double getY() {
        return posY;
    }

    @Override
    public void takeDamage(double amount) {
        health -= amount;
    }

    @Override
    public void move() {
        /*waypioint logic......
        posX ++;
         */
    }
}