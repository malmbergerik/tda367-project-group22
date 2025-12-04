package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.TileBase;
import td_game.model.towers.attackStrategies.IAttackStrategy;
import td_game.model.towers.cooldownStrategies.ICooldownStrategy;
import td_game.model.towers.rangeStrategies.IRangeStrategy;
import td_game.model.towers.targetStrategy.ITargetStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ATower implements IPositionable, IPlacementRule {

    private int x;
    private int y;
    protected List<ABaseEnemy> enemiesInRange = new ArrayList<>();
    protected IAttackStrategy attackStrategy;
    protected IRangeStrategy rangeStrategy;
    protected ICooldownStrategy cooldownStrategy;
    protected ITargetStrategy targetStrategy;

    public ATower(
            int x, int y,
            IAttackStrategy attackStrategy,
            IRangeStrategy rangeStrategy,
            ICooldownStrategy cooldownStrategy,
            ITargetStrategy targetStrategy)
    {
        this.x = x;
        this.y = y;
        this.attackStrategy = attackStrategy;
        this.rangeStrategy = rangeStrategy;
        this.cooldownStrategy = cooldownStrategy;
        this.targetStrategy = targetStrategy;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setPos(int positionY, int positionX) {
        this.x = positionX;
        this.y = positionY;
    }

    @Override
    public abstract boolean canBePlaced(TileBase tile);

    public void update(Collection<ABaseEnemy> activeEnemies){
        enemiesInRange.clear();
        for(ABaseEnemy enemy : activeEnemies){
            if(rangeStrategy.isInRange(this, enemy.getX(), enemy.getY())){
                enemiesInRange.add(enemy);
            }
        }
        cooldownStrategy.tick();

        if (cooldownStrategy.canShoot(cooldownStrategy.getCoolDownTicks(), this)){
            targetStrategy.selectTarget(this, enemiesInRange)
                    .ifPresent(target -> attackStrategy.attack(this,target));
            cooldownStrategy.reset();
        }

    }

}
