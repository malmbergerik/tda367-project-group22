public abstract class ABaseEnemy implements ITargetable, IMoveable, IDamageable {
    private double health;
    private double speed;
    private double posX;
    private double posY;

    public abstract void takeDamage(double amount);

    public boolean isAlive() {
        if (health <= 0) {
            return false;
        }

        else {
            return true;
        }
    }

    public double getX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public abstract void move();
}