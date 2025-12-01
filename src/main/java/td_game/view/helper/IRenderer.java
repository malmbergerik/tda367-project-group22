package td_game.view.helper;

import java.awt.*;

public interface IRenderer {

    void render(Graphics2D g2);

    default int getRenderPriority() {
        return 0;
    }
}
