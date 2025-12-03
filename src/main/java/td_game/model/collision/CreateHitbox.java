package td_game.model.collision;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class CreateHitbox {
    public static Ellipse2D createHitboxEllipse(Double x, Double y, Double width, Double height){
        Ellipse2D hitbox = new Ellipse2D.Double(x, y, width, height);
        return hitbox;
    }
    public static Rectangle createHitboxRectangle(int x, int y, int width, int height){
        Rectangle hitbox = new Rectangle(x, y, width, height);
        return hitbox;
    }
}
