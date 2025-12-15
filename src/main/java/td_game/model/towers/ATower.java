package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.Tile;
import td_game.model.towers.attackStrategies.IAttackStrategy;
import td_game.model.towers.cooldownStrategies.ICooldownStrategy;
import td_game.model.towers.placementRules.IPlacementRule;
import td_game.model.towers.rangeStrategies.IRangeStrategy;
import td_game.model.towers.targetStrategy.ITargetStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ATower implements IPositionable, IPlacementRule, IValue {

    private int x;
    private int y;
    private int value;
    protected List<ABaseEnemy> enemiesInRange = new ArrayList<>();
    protected IAttackStrategy attackStrategy;
    protected IRangeStrategy rangeStrategy;
    protected ICooldownStrategy cooldownStrategy;
    protected ITargetStrategy targetStrategy;
    protected IPlacementRule placementRule;

    public ATower(
            int x, int y, int value,
            IAttackStrategy attackStrategy,
            IRangeStrategy rangeStrategy,
            ICooldownStrategy cooldownStrategy,
            ITargetStrategy targetStrategy,
            IPlacementRule placementRule)

    {
        this.value = value;
        this.x = x+8;
        this.y = y+8;
        this.attackStrategy = attackStrategy;
        this.rangeStrategy = rangeStrategy;
        this.cooldownStrategy = cooldownStrategy;
        this.targetStrategy = targetStrategy;
        this.placementRule = placementRule;
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
    public boolean canBePlaced(Tile tile) {
        return placementRule.canBePlaced(tile);
    }

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

    @Override
    public int getValue() {
        return value;
    }

    public int getRange() {
        return rangeStrategy.getRange();
    }
}
