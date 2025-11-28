package td_game.model.projectile;

import td_game.model.collision.*;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyManager;
import td_game.model.modelnit.GameModel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Projectile {
    private int pixelsPerTick;
    private int width;
    private int height;
    private int damage;
    private int pierce;
    public int timeAliveTicks;
    private int x;
    private int y;
    private boolean isAlive = true;
    private double angle;
    private ArrayList<ABaseEnemy> enemiesHitThisFrame;
    private GameModel gameModel;

    public Projectile(double angle, int pixelsPerTick, int width, int height, int damage, int pierce, int timeAliveTicks, int x, int y, GameModel gameModel) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.enemiesHitThisFrame = new ArrayList<ABaseEnemy>();
        this.gameModel = gameModel;
    }

    public void move(){
        int dX = (int) (this.pixelsPerTick * Math.cos(this.angle*(Math.PI / 180)));
        int dY = (int) (this.pixelsPerTick * Math.sin(this.angle*(Math.PI / 180)));

        this.x = this.x + dX;
        this.y = this.y + dY;
    }

    public double getY(){
        return this.y;
    }
    public double getX(){
        return this.x;
    }

    public void checkTimerAlive(){
        if ((this.timeAliveTicks <= 0) ) {
            this.isAlive = false;
        }
    }
    public void checkPierceAlive(){
        if ((this.pierce <= 0) ) {
            this.isAlive = false;
        }
    }

    public void hitEnemy()
    {
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();
        for (ABaseEnemy e: enemies) {
            if ((CheckCollision.checkCollision(CreateHitbox.createHitboxEllipse( e.getX(), e.getY(),(double) e.getWidth(), (double) e.getHeight()), CreateHitbox.createHitboxRectangle( this.x, this.y, this.width, this.height))) && ( !enemiesHitThisFrame.contains(e))) {
                e.takeDamage(this.damage);
                this.pierce -= 1;
                if (pierce <= 0){
                    this.isAlive = false;
                    break;
                }
                if (e.isAlive()){
                    enemiesHitThisFrame.add(e);
                }
            }
        }
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public void update(){
        move();

        hitEnemy();
        this.enemiesHitThisFrame.clear();
        this.timeAliveTicks -= 1;
        checkTimerAlive();
        checkPierceAlive();
    }

}
