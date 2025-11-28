package td_game.model.modelnit;

public class MenuState implements IGameState {

    private final GameModel gameModel;

    public MenuState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void enterState() {
        System.out.println("Entering Menu State");
    }

    @Override
    public void exitState() {
        System.out.println("Exiting Menu State");
    }

    @Override
    public void update() {
        System.out.println("Updating Menu State");
    }
}
