package td_game.model.modelnit;

import td_game.model.events.TileUpdateEvent;

public class PausedState implements IGameState{

    @Override
    public void enterState() {
       System.out.println("Game Paused");

    }

    @Override
    public void update() {
        //Does nothing
    }

    @Override
    public void exitState() {
        System.out.print("Game resumed");
    }
}
