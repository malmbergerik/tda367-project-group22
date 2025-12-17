package td_game.model.modelnit;

public class MenuState implements IGameState {

    private final GameModel gameModel;

    public MenuState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void enterState() {}

    @Override
    public void exitState() {}

    @Override
    public void update() {}
}
