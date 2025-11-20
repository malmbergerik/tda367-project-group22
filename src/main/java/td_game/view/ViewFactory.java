package td_game.view;

import td_game.view.GamePanel;
import td_game.view.IView;
import td_game.view.MenuPanel;


public class ViewFactory {
    public IView createView(String viewType, int width, int height) {
        return switch (viewType) {
            case "MENU_VIEW" -> new MenuPanel(width, height);
            case "GAME_VIEW" -> new GamePanel(width, height);
            default -> throw new IllegalArgumentException("Unknown view type: " + viewType);
        };
    }
}
