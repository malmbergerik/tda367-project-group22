package td_game.model.projectile;

import td_game.model.collision.*;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.damageTypeStrategy.IDamagetypeStrategy;
import td_game.model.projectile.lifeTimeStrategy.ILifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.IPierceStrategy;
import td_game.model.projectile.sizeStrategy.ISizeStrategy;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

public class Projectile {

    private double x;
    private double y;
    private double angle;
    private List<ABaseEnemy> enemiesHitThisFrame = new ArrayList<>();

    private IPierceStrategy pierceStrategy;
    private IMovementStrategy movementStrategy;
    private IDamagetypeStrategy damagetypeStrategy;
    private ILifeTimeStrategy lifeTimeStrategy;
    private ISizeStrategy sizeStrategy;
    private String projectileName;

    public Projectile(double angle, double x, double y, IMovementStrategy movementStrategy , IPierceStrategy pierceStrategy, IDamagetypeStrategy damagetypeStrategy, ILifeTimeStrategy lifeTimeStrategy, ISizeStrategy sizeStrategy, String projecileName) {
        this.angle = angle;
        this.x = x + 16 / 2; // TODO: Make this cleaner, spawn projectiles in center of tile.
        this.y = y + 16 / 2; // TODO: Make this cleaner, spawn projectiles in center of tile.

        this.sizeStrategy = sizeStrategy;
        this.lifeTimeStrategy = lifeTimeStrategy;
        this.damagetypeStrategy = damagetypeStrategy;
        this.movementStrategy = movementStrategy;
        this.pierceStrategy = pierceStrategy;
        this.projectileName = projecileName;
    }



    public double getX() { return  Math.round(x); }
    public double getY() { return  Math.round(y); }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public int getWidth() { return sizeStrategy.getWidth(); }
    public int getHeight() { return sizeStrategy.getHeight(); }
    public double getAngle() { return angle; }
    public RectangularShape getHitbox() {return sizeStrategy.getHitbox(this);}
    public void reducePierce() { pierceStrategy.reducePierce();}
    public int getDamage() {return damagetypeStrategy.getDamage();}
    public boolean getPierce() {return pierceStrategy.hasPierceLeft();}
    public void move() {movementStrategy.move(this);}
    public void reduceTimeAlive() {lifeTimeStrategy.updateLifetime();}

    public boolean getIsAlive() {
        return lifeTimeStrategy.isAlive(this);
    }

    public List<ABaseEnemy> getEnemiesHitThisFrame() {
        return enemiesHitThisFrame;
    }

    public void addEnemyHit(ABaseEnemy e) {
        enemiesHitThisFrame.add(e);
    }

    public void clearEnemiesHitThisFrame() {
        enemiesHitThisFrame.clear();
    }

    // Fix later
    public String getName() {
        return this.projectileName;
    }


}
