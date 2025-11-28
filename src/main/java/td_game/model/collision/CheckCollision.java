package td_game.model.collision;

import java.awt.*;
import java.awt.geom.RectangularShape;

public abstract class CheckCollision {

    public static Boolean checkCollision(RectangularShape box1, RectangularShape box2){
        if  (box1.getBounds2D().intersects(box2.getBounds2D())){
            return true;
        }
        return false;
    }
}
