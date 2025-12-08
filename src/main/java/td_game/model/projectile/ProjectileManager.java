package td_game.model.projectile;

import td_game.model.collision.CheckCollision;
import td_game.model.collision.CreateHitbox;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.events.ProjectileUpdateEvent;
import td_game.model.modelnit.GameModel;

import java.awt.geom.RectangularShape;
import java.util.List;

public class ProjectileManager {

    private List<Projectile> activeProjectiles;
    private GameModel gameModel;

    public ProjectileManager(GameModel gameModel) {
        this.gameModel = gameModel;
        this.activeProjectiles = gameModel.getActiveProjectiles();
    }
    public void createProjectile(double angle, int pixelsPerTick, int width, int height, int damage, int pierce, int timeAliveTicks, int x, int y, boolean hitBoxRound) {
        Projectile p = new Projectile(angle, pixelsPerTick, width, height,  damage, pierce, timeAliveTicks,  x, y, hitBoxRound);
        activeProjectiles.add(p);
    }
    public void addProjectile(Projectile p) {
        activeProjectiles.add(p);
    }

    public RectangularShape getProjectileHitBox(Projectile p){
        if (p.getHitBoxRound()) {
            return CreateHitbox.createHitboxEllipse((double) p.getX(),(double) p.getY(),(double) p.getWidth(),(double) p.getHeight());
        } else {
            return CreateHitbox.createHitboxRectangle((int) p.getX(),(int) p.getY(), p.getWidth(), p.getHeight());
        }
    }

    public List<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }

    public void hitEnemy(Projectile p)
    {
        RectangularShape hitBox = getProjectileHitBox(p);
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();
        for (ABaseEnemy e: enemies) {
            RectangularShape enemyHitBox = CreateHitbox.createHitboxRectangle((int) e.getX(),(int) e.getY(), e.getWidth(), e.getHeight());
            if (!p.getEnemiesHitThisFrame().contains(e) && CheckCollision.checkCollision(hitBox, enemyHitBox)) {
                e.takeDamage(p.getDamage());
                p.reducePierce();
                if (p.getPierce() <= 0){
                    break;
                }
                if (e.isAlive()){
                    p.addEnemyHit(e);
                }
            }
        }
    }

    public void update() {
        for (int i = activeProjectiles.size() - 1; i >= 0; i--) {
            Projectile p = activeProjectiles.get(i);
            p.move();
            hitEnemy(p);
            p.reduceTimeAlive();

            if (!p.getIsAlive()) {
                activeProjectiles.remove(i);
            }

            gameModel.notifyObserver(new ProjectileUpdateEvent());


        }
    }
}
