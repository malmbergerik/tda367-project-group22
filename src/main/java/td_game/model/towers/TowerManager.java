package td_game.model.towers;

import td_game.model.events.TowersUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.player.MoneySystem;

import java.util.ArrayList;
import java.util.List;


/**
 * The TowerManager class is responsible for managing the lifecycle of all towers in the game.
 * It handles the addition (purchase), removal (selling), and updating of tower entities.
 * It acts as a bridge between the game model, the economic system, and the individual tower logic.
 */
public class TowerManager {
    private List<ATower> activeTowers;
    private ATower[][] towergrid;
    private GameModel gameModel;
    private MoneySystem moneySystem;

    /**
     * Constructs a new TowerManager.
     * Initializes references to the game model and money system to coordinate game state changes.
     *
     * @param gameModel   The central game model containing the active enemies and tower grid.
     * @param moneySystem The system responsible for handling player currency transactions.
     */
    public TowerManager(GameModel gameModel, MoneySystem moneySystem)
    {
        this.gameModel = gameModel;
        this.moneySystem = moneySystem;
        this.activeTowers = gameModel.getActiveTowers();
        this.towergrid = gameModel.getPlacedTowerGrid();
    }

    /**
     * Attempts to add a tower to the active game state.
     * Verifies if the player has enough funds before finalizing the purchase.
     *
     * @param tower The tower entity to be added.
     * @return true if the tower was successfully purchased and added, false if funds were insufficient.
     */
    public boolean addTower(ATower tower) {
        if (moneySystem.canAfford(tower.getValue())) {
            moneySystem.handleTowerPurchase(tower.getValue());
            activeTowers.add(tower);

            return true;

        }
        return false;
    }

    /**
     * Removes a tower from the active game state (selling it).
     * Refunds a portion (typically 50%) of the tower's value to the player.
     *
     * @param tower The tower entity to remove.
     */
    public void removeTower(ATower tower) {
        moneySystem.handleTowerSell(tower.getValue() / 2);
        activeTowers.remove(tower);
    }

    /**
     * Updates the state of all active towers.
     * Delegates the logic to each tower's update method (scanning for enemies, cooling down, firing).
     * Triggers a TowersUpdateEvent to notify the view of any changes (e.g., rotation).
     */
    public void update(){
        for (ATower t: activeTowers)
        {
            t.update(gameModel.getActiveEnemies());
        }
        gameModel.notifyObserver(new TowersUpdateEvent());

    }
}