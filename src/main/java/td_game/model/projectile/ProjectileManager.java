package td_game.model.projectile;

import td_game.model.collision.CheckCollision;
import td_game.model.collision.CreateHitbox;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.events.ProjectileUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.damageTypeStrategy.IDamagetypeStrategy;
import td_game.model.projectile.lifeTimeStrategy.ILifeTimeStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.IPierceStrategy;
import td_game.model.projectile.sizeStrategy.BasicRoundSizeStrategy;
import td_game.model.projectile.sizeStrategy.ISizeStrategy;

import java.awt.geom.RectangularShape;
import java.util.List;

public class ProjectileManager {

    private List<Projectile> activeProjectiles;
    private GameModel gameModel;

    public ProjectileManager(GameModel gameModel) {
        this.gameModel = gameModel;
        this.activeProjectiles = gameModel.getActiveProjectiles();
    }
    public void createProjectile(double angle, int x, int y, IMovementStrategy movementStrategy , IPierceStrategy pierceStrategy, IDamagetypeStrategy damagetypeStrategy, ILifeTimeStrategy lifeTimeStrategy, ISizeStrategy sizeStrategy) {
        Projectile p = new Projectile(angle, x,y,movementStrategy , pierceStrategy,  damagetypeStrategy,  lifeTimeStrategy, sizeStrategy );
        activeProjectiles.add(p);
    }
    public void addProjectile(Projectile p) {
        activeProjectiles.add(p);
    }


    public List<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }

    public void hitEnemy(Projectile p)
    {
        RectangularShape hitBox = p.getHitbox();
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();
        for (ABaseEnemy e: enemies) {
            RectangularShape enemyHitBox = CreateHitbox.createHitboxRectangle((int) e.getX(),(int) e.getY(), e.getWidth(), e.getHeight());
            if (!p.getEnemiesHitThisFrame().contains(e) && CheckCollision.checkCollision(hitBox, enemyHitBox)) {
                e.takeDamage(p.getDamage());
                p.reducePierce();
                if (!p.getPierce()){
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
