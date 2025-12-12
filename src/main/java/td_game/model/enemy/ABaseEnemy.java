package td_game.model.enemy;


import td_game.model.towers.IPositionable;

import java.lang.Math;

public abstract class ABaseEnemy implements IMoveable, IDamageable ,IDamageDealer{
    protected int health;
    protected double speed;
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;
    protected int damageAmount;


    public ABaseEnemy (int health, double speed, int width, int height, int damageAmount) {
        this.health = health;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.damageAmount = damageAmount;
    }

    public abstract boolean hasReachedEnd();

    @Override
    public abstract void takeDamage(int amount);

    @Override
    public boolean isAlive() {
        return health > 0;
    }


    public double getX() {
        return posX;
    }


    public double getY() {
        return posY;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getHealth() {return  health;}

    @Override
    public abstract void move();

    public abstract String getName();

    public int getDamageAmount(){return damageAmount;}
}