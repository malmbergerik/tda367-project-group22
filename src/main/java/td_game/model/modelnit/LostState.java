package td_game.model.modelnit;

import td_game.model.enemy.EnemyManager;
import td_game.model.player.*;

public class LostState implements IGameState{

    @Override
    public void enterState() {
        System.out.println("Game Lost");
    }

    @Override
    public void update() {
        //Does nothing
    }

    @Override
    public void exitState() {

    }
}
