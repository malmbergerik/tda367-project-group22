package td_game.model.towers;

import td_game.model.events.TowersUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.player.MoneySystem;

import java.util.ArrayList;
import java.util.List;


public class TowerManager {
    private List<ATower> activeTowers = new ArrayList<>();
    private GameModel gameModel;
    private MoneySystem moneySystem;

    public TowerManager(GameModel gameModel, MoneySystem moneySystem)
    {
        this.gameModel = gameModel;
        this.moneySystem = moneySystem;
        this.activeTowers = gameModel.getActiveTowers();
    }

    public boolean addTower(ATower tower) {
        if (moneySystem.canAfford(tower.getValue())) {
            moneySystem.handleTowerPurchase(tower.getValue());
            activeTowers.add(tower);

            return true;

        }
        return false;
    }

    public void removeTower(ATower tower) {
        moneySystem.handleTowerSell(tower.getValue() / 2);
        activeTowers.remove(tower);
    }

    public void update(){
        for (ATower t: activeTowers)
        {
            t.update(gameModel.getActiveEnemies());
        }
        gameModel.notifyObserver(new TowersUpdateEvent());

    }
}
