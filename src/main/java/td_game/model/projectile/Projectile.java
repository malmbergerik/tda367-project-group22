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

/**
 * The Projectile class represents a moving entity fired by a tower.
 * It acts as a context object for the Strategy Pattern, delegating specific behaviors
 * (movement, damage, collision size, lifetime) to injected strategy interfaces.
 * This allows for highly customizable projectile variations without subclassing.
 */
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

    /**
     * Constructs a new Projectile with the specified strategies.
     *
     * @param angle             The angle of travel in degrees.
     * @param x                 The starting x-coordinate (top-left of the tile).
     * @param y                 The starting y-coordinate (top-left of the tile).
     * @param movementStrategy  Defines how the projectile moves (speed, trajectory).
     * @param pierceStrategy    Defines how many enemies the projectile can pass through.
     * @param damagetypeStrategy Defines the damage amount and type.
     * @param lifeTimeStrategy  Defines how long the projectile lasts before despawning.
     * @param sizeStrategy      Defines the physical dimensions and hitbox of the projectile.
     * @param projecileName     The display name or identifier for the projectile type.
     */
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

    /**
     * Retrieves the hitbox for collision detection.
     * Delegates the shape creation to the SizeStrategy.
     *
     * @return A RectangularShape representing the physical bounds of the projectile.
     */
    public RectangularShape getHitbox() {return sizeStrategy.getHitbox(this);}

    /**
     * Reduces the remaining pierce count of the projectile.
     * Called when the projectile successfully hits an enemy.
     */
    public void reducePierce() { pierceStrategy.reducePierce();}

    public int getDamage() {return damagetypeStrategy.getDamage();}

    /**
     * Checks if the projectile can still pierce through more enemies.
     *
     * @return true if pierce count > 0, false otherwise.
     */
    public boolean getPierce() {return pierceStrategy.hasPierceLeft();}

    /**
     * Updates the projectile's position based on its MovementStrategy.
     */
    public void move() {movementStrategy.move(this);}

    /**
     * Updates the remaining lifetime of the projectile.
     * Usually decrements a tick counter or checks a timestamp.
     */
    public void reduceTimeAlive() {lifeTimeStrategy.updateLifetime();}

    /**
     * Checks if the projectile is still active in the game world.
     * A projectile dies if its lifetime expires or its pierce is exhausted.
     *
     * @return true if the projectile should remain in the world, false otherwise.
     */
    public boolean getIsAlive() {
        return lifeTimeStrategy.isAlive(this);
    }

    /**
     * Retrieves the list of enemies hit during the current update frame.
     * Used to prevent a single projectile from hitting the same enemy multiple times in one tick
     * or for chaining logic.
     *
     * @return The list of enemies hit this frame.
     */
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