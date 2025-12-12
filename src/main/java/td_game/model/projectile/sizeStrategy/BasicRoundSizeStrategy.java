package td_game.model.projectile.sizeStrategy;

import td_game.model.collision.CreateHitbox;
import td_game.model.projectile.Projectile;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class BasicRoundSizeStrategy implements ISizeStrategy {

    private boolean isRound;
    private int height;
    private int width;

    public BasicRoundSizeStrategy(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public RectangularShape getHitbox(Projectile p) {
        return CreateHitbox.createHitboxEllipse(p.getX(), p.getY(), (double) p.getWidth(), (double) p.getHeight());

    }
}


