package td_game.view.render;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ABaseRenderer implements IRenderer {
    protected final RenderingContext context;

    public ABaseRenderer(RenderingContext context) {
        this.context = context;
    }

    protected int scale(double value) {
        return (int) value * context.getScale();
    }

    protected int toScreenX(double x) {
        return scale(x);
    }

    protected int toScreenY(double y) {
        return scale(y);
    }

    protected void drawCenteredImage(Graphics2D g2, BufferedImage image, double x, double y, int width, int height) {
        int scaledWidth = scale(width);
        int scaledHeight = scale(height);

        int screenX = toScreenX(x) - scaledWidth / 2;
        int screenY = toScreenY(y) - scaledHeight / 2;
        g2.drawImage(image, screenX, screenY,scaledWidth, scaledHeight, null);
    }

    protected void drawAtTile(Graphics2D g2, BufferedImage image, int row, int col, int tileSize) {
        int scaledTileSize = scale(tileSize);
        int screenX = col * scaledTileSize;
        int screenY = row * scaledTileSize;
        g2.drawImage(image, screenX, screenY, scaledTileSize, scaledTileSize, null);
    }

    protected void fillTile(Graphics2D g2, Color color, int row, int col, int tileSize) {
        g2.setColor(color);

        int x = scale(col * tileSize);
        int y = scale(row * tileSize);
        int size = scale(tileSize);

        g2.fillRect(x, y, size, size);
    }

}
