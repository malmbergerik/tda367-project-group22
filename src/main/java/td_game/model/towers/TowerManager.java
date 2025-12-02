package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private List<Tower> activeTowers = new ArrayList<>();
    private ProjectileFactory projectileFactory;
    private GameModel gameModel;

    public TowerManager(ProjectileFactory projectileFactory, GameModel gameModel )
    {
        this.projectileFactory = projectileFactory;
        this.gameModel = gameModel;
    }
    public void addTower(Tower tower) {
        activeTowers.add(tower);
    }


    public double lenTooEnemy(Tower t,ABaseEnemy enemy)
    {
        return  Math.sqrt(Math.pow(enemy.getX() - (t.getX()),2) + Math.pow(enemy.getY()- (t.getY()),2));
    }
    public double getAngleToEnemy(Tower t,ABaseEnemy enemy)
    {
        return  Math.atan2(enemy.getY()- (t.getY()), enemy.getX() - (t.getX())) * (180/Math.PI);
    }


    public ArrayList<ABaseEnemy> getEnemyInRangeInOrder(Tower t,List<ABaseEnemy> enemies)
    {
        if (enemies.isEmpty()) {return enemyInRange;}

        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(t,e) <= t.attackRange) && !enemyInRange.contains(e))
            {
                enemyInRange.add(e);
            }
            if ((lenTooEnemy(t,e) > this.attackRange) && enemyInRange.contains(e))
            {
                enemyInRange.remove(e);
            }
        }
        return enemyInRange;
    }

    public void shoot(Tower t)
    {
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();

        if (enemies == null){return;}
        if (getEnemyInRangeInOrder(t,enemies).isEmpty()) {return;}
        ABaseEnemy firstEnemyInRange = getEnemyInRangeInOrder(t,enemies).get(0);

        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(t,e) < t.getAttackRange()) && (t.checkIfCanShoot()))
            {
                for (int i=0; i<= t.getProjectileAmount(); i++) {
                    projectileFactory.create((getAngleToEnemy(t,(firstEnemyInRange )) - 15*Math.round(0.5*t.getProjectileAmount()) + 15*i),t.x,t.y);
                }
                t.resetCooldown();
                return;
            }
        }
    }

    public void update(){
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();
        for (Tower t: activeTowers)
        {

        }

    }
}
