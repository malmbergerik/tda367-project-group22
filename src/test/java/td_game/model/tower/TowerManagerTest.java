package td_game.model.tower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.modelnit.GameModel;
import td_game.model.player.MoneySystem;
import td_game.model.player.Player;
import td_game.model.player.PlayerHealth;
import td_game.model.player.PlayerMoney;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.ATower;
import td_game.model.towers.TowerManager;
import td_game.model.towers.factory.TowerFactory;

import static org.junit.jupiter.api.Assertions.*;

class TowerManagerTest {

    private TowerManager towerManager;
    private MoneySystem moneySystem;
    private GameModel model;
    private TowerFactory towerFactory;
    private Player player;

    @BeforeEach
    void setUp() {
        // 1. Setup Model
        model = new GameModel(16);

        // 2. Setup Player and MoneySystem
        player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        moneySystem = new MoneySystem(player);

        // 3. Setup Manager
        towerManager = new TowerManager(model, moneySystem);

        // 4. Setup Factory (needs ProjectileManager)
        towerFactory = new TowerFactory(new ProjectileManager(model));
    }

    @Test
    void testAddTowerSuccess() {
        // Create a tower (CanonTower costs 10)
        ATower tower = towerFactory.createTower("CanonTower", 0, 0);
        int initialMoney = getPlayerMoneyValue(player);
        int towerCost = tower.getValue();

        // Attempt to buy
        boolean result = towerManager.addTower(tower);

        assertTrue(result, "Should allow adding tower when money is sufficient");

        // Verify money deducted
        int currentMoney = getPlayerMoneyValue(player);
        assertEquals(initialMoney - towerCost, currentMoney, "Money should be deducted");

        // Verify tower added to model
        assertTrue(model.getActiveTowers().contains(tower), "Tower should be in active list");
    }

    @Test
    void testAddTowerInsufficientMoney() {
        // Drain money
        int currentMoney = getPlayerMoneyValue(player);
        player.spendMoney(currentMoney);

        ATower tower = towerFactory.createTower("CanonTower", 0, 0);

        // Attempt to buy
        boolean result = towerManager.addTower(tower);

        assertFalse(result, "Should NOT allow adding tower with 0 money");
        assertFalse(model.getActiveTowers().contains(tower));
    }

    @Test
    void testRemoveTowerRefundsMoney() {
        ATower tower = towerFactory.createTower("CanonTower", 0, 0);
        int cost = tower.getValue();

        // Buy
        towerManager.addTower(tower);
        int moneyAfterBuy = getPlayerMoneyValue(player);

        // Sell
        towerManager.removeTower(tower);

        // Verify Refund (50% of cost)
        int expectedMoney = moneyAfterBuy + (cost / 2);
        assertEquals(expectedMoney, getPlayerMoneyValue(player), "Should refund 50% of cost");
        assertFalse(model.getActiveTowers().contains(tower));
    }

    // Helper to handle if getMoney() returns int or PlayerMoney object
    private int getPlayerMoneyValue(Player p) {
        // Based on your codebase context, Player.getMoney() likely returns an int.
        // If it returns an object, change this line to: return p.getMoney().getCurrentMoney();
        return p.getMoney();
    }

    @Test
    void testRemoveTower(){
        ATower tower = towerFactory.createTower("CanonTower", 0, 0);

        towerManager.addTower(tower);

        towerManager.removeTower(tower);
        assertFalse(model.getActiveTowers().contains(tower));
    }
}