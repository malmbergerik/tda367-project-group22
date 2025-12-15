package td_game.view.render;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An abstract base class for renderers providing common rendering utilities.
 */
public abstract class ABaseRenderer implements IRenderer {
    protected final RenderingContext context;

    public ABaseRenderer(RenderingContext context) {
        this.context = context;
    }

    /**
     * Scales a value based on the rendering context's scale factor.
     *
     * @param value The value to scale.
     * @return The scaled integer value.
     */
    protected int scale(double value) {
        return (int) value * context.getScale();
    }

    /**
     * Converts a game coordinate to a screen X coordinate.
     *
     * @param x The game X coordinate.
     * @return The screen X coordinate.
     */
    protected int toScreenX(double x) {
        return scale(x);
    }

    /**
     * Converts a game coordinate to a screen Y coordinate.
     *
     * @param y The game Y coordinate.
     * @return The screen Y coordinate.
     */
    protected int toScreenY(double y) {
        return scale(y);
    }

    /**
     * Draws an image centered at the specified game coordinates.
     *
     * @param g2    The graphics context.
     * @param image The image to draw.
     * @param x     The game X coordinate.
     * @param y     The game Y coordinate.
     * @param width The width to draw the image.
     * @param height The height to draw the image.
     */
    protected void drawCenteredImage(Graphics2D g2, BufferedImage image, double x, double y, int width, int height) {
        int scaledWidth = scale(width);
        int scaledHeight = scale(height);

        int screenX = toScreenX(x) - scaledWidth / 2;
        int screenY = toScreenY(y) - scaledHeight / 2;
        g2.drawImage(image, screenX, screenY,scaledWidth, scaledHeight, null);
    }

    /**
     * Draws an image at the specified tile position.
     *
     * @param g2      The graphics context.
     * @param image   The image to draw.
     * @param row     The tile row.
     * @param col     The tile column.
     * @param tileSize The size of the tile.
     */
    protected void drawAtTile(Graphics2D g2, BufferedImage image, int row, int col, int tileSize) {
        int scaledTileSize = scale(tileSize);
        int screenX = col * scaledTileSize;
        int screenY = row * scaledTileSize;
        g2.drawImage(image, screenX, screenY, scaledTileSize, scaledTileSize, null);
    }

    /**
     * Fills a tile at the specified position with the given color.
     *
     * @param g2      The graphics context.
     * @param color   The color to fill.
     * @param row     The tile row.
     * @param col     The tile column.
     * @param tileSize The size of the tile.
     */
    protected void fillTile(Graphics2D g2, Color color, int row, int col, int tileSize) {
        g2.setColor(color);

        int x = scale(col * tileSize);
        int y = scale(row * tileSize);
        int size = scale(tileSize);

        g2.fillRect(x, y, size, size);
    }

    protected void fillCircle(Graphics2D g2, Color color, int row, int col, int tilesize, int diameter){
        g2.setColor(color);

        int scaledDiameter = scale(diameter);

        int x = (scale(col * tilesize) - scaledDiameter /2) + scale(tilesize/2);
        int y = (scale(row * tilesize) - scaledDiameter /2) + scale(tilesize/2);
        g2.fillOval(x,y,scaledDiameter,scaledDiameter);
    }

}
