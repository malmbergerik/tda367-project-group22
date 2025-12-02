package td_game.model.enemy;


import java.lang.Math;

public abstract class ABaseEnemy implements ITargetable, IMoveable, IDamageable {
    protected int health;
    protected double speed;
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;


    public ABaseEnemy (int health, double speed, int width, int height) {
        this.health = health;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public abstract boolean hasReachedEnd();

    @Override
    public abstract void takeDamage(int amount);

    @Override
    public boolean isAlive() {
        return health > 0;
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
    public int getWidth() {return width;}

    @Override
    public int getHeight() {return height;}

    public int getHealth() {return  health;}

    @Override
    public abstract void move();
}