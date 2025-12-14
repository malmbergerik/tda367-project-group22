package td_game.view.render;

import java.awt.*;

/**
 * An interface for rendering game components.
 */
public interface IRenderer {

    void render(Graphics2D g2);

    /**
     * Returns the render priority of this renderer.
     * Higher priority renderers are rendered on top of lower priority ones.
     *
     * @return the render priority
     */
    default int getRenderPriority() {
        return 0;
    }
}
