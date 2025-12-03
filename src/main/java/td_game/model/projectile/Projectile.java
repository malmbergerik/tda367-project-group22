package td_game.model.projectile;

import td_game.model.collision.*;
import td_game.model.enemy.ABaseEnemy;

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

    private final boolean hitBoxRound;

    public Projectile(double angle, int pixelsPerTick, int width, int height, int damage, int pierce, int timeAliveTicks, int x, int y, boolean hitBoxRound) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.angle = angle;
        this.x = x;
        this.y = y;

        this.hitBoxRound = hitBoxRound;
    }

    public void move(){
        x += pixelsPerTick * Math.cos(angle * Math.PI / 180);
        y += pixelsPerTick * Math.sin(angle * Math.PI / 180);
    }

    public int getX() { return (int) Math.round(x); }
    public int getY() { return (int) Math.round(y); }
    public int getDamage() { return damage; }
    public int getPierce() { return pierce; }
    public int getTimeAliveTicks() { return timeAliveTicks; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getAngle() { return angle; }
    public boolean getHitBoxRound() { return hitBoxRound; }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public boolean getIsAlive() {
        return (pierce > 0 && timeAliveTicks > 0 && this.alive);
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
    public void reducePierce() { pierce--; }
    public void reduceTimeAlive() { timeAliveTicks--; }

    // Fix later
    public String getName() {
        return "Projectile";
    }


}
