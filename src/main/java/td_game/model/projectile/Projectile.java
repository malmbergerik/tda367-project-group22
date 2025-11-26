package td_game.model.projectile;

import td_game.model.enemy.ABaseEnemy;

import java.awt.*;
import java.util.ArrayList;

public class Projectile {
    private int pixelsPerMs;
    private int width;
    private int height;
    private int damage;
    private int pierce;
    private int timeAlive;
    private int x;
    private int y;
    private long startTimeAlive = System.currentTimeMillis(); //är enbart i millisekunder
    private boolean isAlive = true;
    private ArrayList<ABaseEnemy> testEnemyList; //Test lista för att kunna testa koden
    private double angle;
    private ArrayList<ABaseEnemy> enemiesHitThisFrame;

    public Projectile(double angle,int pixelsPerMs, int width, int height, int damage, int pierce, int timeAlive, int x, int y) {
        this.pixelsPerMs = pixelsPerMs;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAlive = timeAlive;
        this.testEnemyList = new ArrayList<ABaseEnemy>();
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.enemiesHitThisFrame = new ArrayList<ABaseEnemy>();
    }

    public void move(double angle){
        int dX = (int) (this.pixelsPerMs * Math.cos(angle*(Math.PI / 180)));
        int dY = (int) (this.pixelsPerMs * Math.sin(angle*(Math.PI / 180)));

        this.x = this.x + dX;
        this.y = this.y + dY;
    }

    public double getY(){
        return this.y;
    }
    public double getX(){
        return this.x;
    }

    public Rectangle createHitbox(int x, int y,int width, int height){
        Rectangle rectangle = new Rectangle(x,y,width,height);
        return rectangle;
    }

    public Boolean checkCollision(Rectangle box1, Rectangle box2){
        if  (box1.intersects(box2)){
            return true;
        }
        return false;
    }

    public void collisionProjectileEnemy()
    {
        ArrayList<ABaseEnemy> enemies = this.testEnemyList;
        if ((this.startTimeAlive + this.timeAlive < System.currentTimeMillis()) || (this.pierce <= 0)) {
            this.isAlive = false;
            return;
        }
        for (ABaseEnemy e: enemies) {
            if ((checkCollision(createHitbox((int) e.getX(), (int) e.getY(),e.getWidth(), e.getHeight()), createHitbox(this.x, this.y, this.width, this.height))) && ( !enemiesHitThisFrame.contains(e))) {
                e.takeDamage(this.damage);
                pierce -= 1;
                if (pierce <= 0){
                    isAlive = false;
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
        move(this.angle);

        collisionProjectileEnemy();
        this.enemiesHitThisFrame.clear();
    }

}
