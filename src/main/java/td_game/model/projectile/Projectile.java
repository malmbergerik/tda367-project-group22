package td_game.model.projectile;

import td_game.model.collision.*;
import td_game.model.enemy.ABaseEnemy;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
    private ArrayList<ABaseEnemy> testEnemyList; //Test lista för att kunna testa koden
    private double angle;
    private ArrayList<ABaseEnemy> enemiesHitThisFrame;

    public Projectile(double angle,int pixelsPerTick, int width, int height, int damage, int pierce, int timeAliveTicks, int x, int y) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.testEnemyList = new ArrayList<ABaseEnemy>();
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.enemiesHitThisFrame = new ArrayList<ABaseEnemy>();
    }
    public void addTestEnemyList(ABaseEnemy enemy){
        testEnemyList.add(enemy);
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
        ArrayList<ABaseEnemy> enemies = this.testEnemyList;
        for (ABaseEnemy e: enemies) {
            if ((CheckCollision.checkCollision(CreateHitbox.createHitboxEllipse( e.getX(), e.getY(),(double) e.getWidth(), (double) e.getHeight()), CreateHitbox.createHitboxRectangle( this.x, this.y, this.width, this.height))) && ( !enemiesHitThisFrame.contains(e))) {
                e.takeDamage(this.damage);
                pierce -= 1;
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
