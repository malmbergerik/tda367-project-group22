package td_game.view.helper;

/**
 * A class to hold data about a projectile for view purposes.
 */
public class ProjectileViewData {
    private final String name;
    private final double x;
    private final double y;
    private final int heigth;
    private final int width;

    public ProjectileViewData(String name, double x, double y, int height, int width) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.heigth = height;
        this.width = width;
    }

    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return heigth; }
}
