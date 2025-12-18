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
import td_game.model.projectile.sizeStrategy.ISizeStrategy;

import java.awt.geom.RectangularShape;
import java.util.List;

/**
 * The ProjectileManager class handles the lifecycle and logic for all active projectiles in the game.
 * It is responsible for updating projectile positions, managing their lifetime, and detecting collisions with enemies.
 * Acts as the central system for projectile physics and damage application.
 */
public class ProjectileManager {

    private List<Projectile> activeProjectiles;
    private GameModel gameModel;

    /**
     * Constructs a new ProjectileManager.
     * Initializes the list of active projectiles by referencing the list in the GameModel.
     *
     * @param gameModel The central game model providing access to enemies and game state.
     */
    public ProjectileManager(GameModel gameModel) {
        this.gameModel = gameModel;
        this.activeProjectiles = gameModel.getActiveProjectiles();
    }

    /**
     * Adds a new projectile to the active simulation.
     *
     * @param p The projectile instance to add.
     */
    public void addProjectile(Projectile p) {
        activeProjectiles.add(p);
    }


    public List<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }

    /**
     * Checks for collisions between a specific projectile and all active enemies.
     * If a collision occurs, it applies damage, reduces the projectile's pierce count, and registers the hit.
     * Stops processing if the projectile's pierce is exhausted.
     *
     * @param p The projectile to check for collisions.
     */
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

    /**
     * Updates the state of all active projectiles.
     * Iterates backwards through the list to safely remove dead projectiles.
     * For each projectile: moves it, checks collisions, updates lifetime, and removes it if expired.
     * Triggers a ProjectileUpdateEvent to notify the view.
     */
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