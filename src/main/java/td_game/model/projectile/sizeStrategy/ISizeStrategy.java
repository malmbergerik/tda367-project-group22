package td_game.model.projectile.sizeStrategy;

import td_game.model.projectile.Projectile;

import java.awt.*;
import java.awt.geom.RectangularShape;

public interface ISizeStrategy {

    int getWidth();
    int getHeight();
    RectangularShape getHitbox(Projectile p);

}
