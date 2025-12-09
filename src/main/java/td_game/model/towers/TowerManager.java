package td_game.model.towers;

import td_game.model.events.TowersUpdateEvent;
import td_game.model.modelnit.GameModel;

import java.util.ArrayList;
import java.util.List;


public class TowerManager {
    private List<ATower> activeTowers = new ArrayList<>();
    private GameModel gameModel;

    public TowerManager(GameModel gameModel )
    {
        this.gameModel = gameModel;
        this.activeTowers = gameModel.getActiveTowers();
    }
    public void addTower(ATower tower) {
        activeTowers.add(tower);
    }

    public void removeTower(ATower tower) {
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
