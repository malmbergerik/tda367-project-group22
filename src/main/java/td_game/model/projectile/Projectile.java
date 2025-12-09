package td_game.model.projectile;

import td_game.model.collision.*;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.damageTypeStrategy.IDamagetypeStrategy;
import td_game.model.projectile.lifeTimeStrategy.ILifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.IPierceStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Projectile {
    private int pixelsPerTick;
    private int width;
    private int height;
    private int damage;
    private int pierce;
    private int timeAliveTicks;
    private double x;
    private double y;
    private double angle;
    private boolean alive = true;
    private List<ABaseEnemy> enemiesHitThisFrame = new ArrayList<>();

    private IPierceStrategy pierceStrategy;
    private IMovementStrategy movementStrategy;
    private IDamagetypeStrategy damagetypeStrategy;
    private ILifeTimeStrategy lifeTimeStrategy;

    private final boolean hitBoxRound;

    public Projectile(IMovementStrategy movementStrategy , IPierceStrategy pierceStrategy, IDamagetypeStrategy damagetypeStrategy,ILifeTimeStrategy lifeTimeStrategy, double angle, int width, int height, int timeAliveTicks, double x, double y, boolean hitBoxRound) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.angle = angle;
        this.x = x + 16 / 2; // TODO: Make this cleaner, spawn projectiles in center of tile.
        this.y = y + 16 / 2; // TODO: Make this cleaner, spawn projectiles in center of tile.

        this.lifeTimeStrategy = lifeTimeStrategy;
        this.damagetypeStrategy = damagetypeStrategy;
        this.movementStrategy = movementStrategy;
        this.pierceStrategy = pierceStrategy;
        this.hitBoxRound = hitBoxRound;
    }



    public double getX() { return  Math.round(x); }
    public double getY() { return  Math.round(y); }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getAngle() { return angle; }
    public boolean getHitBoxRound() { return hitBoxRound; }


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
        return "Bullet";
    }


}
