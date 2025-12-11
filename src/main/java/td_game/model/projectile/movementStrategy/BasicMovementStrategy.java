package td_game.model.projectile.movementStrategy;

import td_game.model.projectile.Projectile;
import td_game.model.projectile.damageTypeStrategy.BasicDamageTypeStrategy;

public class BasicMovementStrategy implements IMovementStrategy{
    private double dX;
    private double dY;
    private int pixelsPerTick;

    public BasicMovementStrategy(int pixelsPerTick){
        this.pixelsPerTick = pixelsPerTick;
    }

    public BasicMovementStrategy copy() {
        return new BasicMovementStrategy(this.pixelsPerTick);
    }

    @Override
    public void move(Projectile p){
        dX += pixelsPerTick * Math.cos(p.getAngle() * Math.PI / 180);
        dY += pixelsPerTick * Math.sin(p.getAngle() * Math.PI / 180);
        p.setX(dX + p.getX());
        p.setY(dY + p.getY());
    }
}
