package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;


public class TowerManager {
    private List<Tower> activeTowers = new ArrayList<>();
    private GameModel gameModel;
    private ProjectileFactory projectileFactory;

    public TowerManager(ProjectileFactory projectileFactory, GameModel gameModel )
    {

        this.gameModel = gameModel;
        this.projectileFactory = projectileFactory;
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
        ArrayList<ABaseEnemy> list = t.getEnemiesInRange();

        if (enemies.isEmpty()) {return list;}

        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(t,e) <= t.getAttackRange()) && !list.contains(e))
            {
                list.add(e);
            }
            if ((lenTooEnemy(t,e) > t.getAttackRange()) && list.contains(e))
            {
                list.remove(e);
            }
        }
        return list;
    }

    public void shoot(Tower t, ABaseEnemy target)
    {
        for (int i=0; i<= t.getProjectileAmount(); i++) {
            projectileFactory.create((getAngleToEnemy(t,(target )) - 15*Math.round(0.5*t.getProjectileAmount()) + 15*i),(int) t.getX(),(int) t.getY());
        }
    }

    public void update(){
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();
        if (enemies == null){return;}
        for (Tower t: activeTowers)
        {
            t.attackCooldownCounterTick();

            if (getEnemyInRangeInOrder(t,enemies).isEmpty()) {continue;}

            ABaseEnemy target = getEnemyInRangeInOrder(t, enemies).get(0);

            if (!t.checkIfCanShoot()) continue;

            shoot(t, target);
            t.resetCooldown();
        }

    }
}
