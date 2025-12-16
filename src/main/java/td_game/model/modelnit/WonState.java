package td_game.model.modelnit;

public class WonState implements IGameState{

    @Override
    public void enterState() {
        System.out.println("Game Won");
    }

    @Override
    public void update() {
        //Does nothing
    }

    @Override
    public void exitState() {

    }
}
