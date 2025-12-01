package td_game.view;

/**
 * Simple strategy for building Game views.
 */
public class GameViewBuilder implements IViewBuilder {

    @Override
    public IView createView(int width, int height) {
        return new GamePanel(width, height);
    }

    @Override
    public String getViewType() {
        return "GAME_VIEW";
    }
}
