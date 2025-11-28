package td_game.model.projectile;


import td_game.model.enemy.ABaseEnemy;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.security.spec.EllipticCurve;
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

    public Ellipse2D createHitboxEllipse(Double x, Double y,Double width, Double height){
        //Rectangle hitbox = new Rectangle(x, y, width, height);
            Ellipse2D hitbox = new Ellipse2D.Double(x, y, width, height);
        return hitbox;
    }
    public Rectangle createHitboxRectangle(int x, int y,int width, int height){
        Rectangle hitbox = new Rectangle(x, y, width, height);
        return hitbox;
    }

    public Boolean checkCollision(Ellipse2D box1, Rectangle box2){
        if  (box1.getBounds2D().intersects(box2.getBounds2D())){
            return true;
        }
        return false;
    }

    public void collisionProjectileEnemy()
    {
        ArrayList<ABaseEnemy> enemies = this.testEnemyList;
        if ((this.timeAliveTicks <= 0) || (this.pierce <= 0)) {
            this.isAlive = false;
            return;
        }
        for (ABaseEnemy e: enemies) {
            if ((checkCollision(createHitboxEllipse( e.getX(), e.getY(),(double) e.getWidth(), (double) e.getHeight()), createHitboxRectangle( this.x, this.y, this.width, this.height))) && ( !enemiesHitThisFrame.contains(e))) {
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

        collisionProjectileEnemy();
        this.enemiesHitThisFrame.clear();
        this.timeAliveTicks -= 1;
    }

}
