package td_game.view.helper;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class EnemyViewData {
    private String enemy;
    private double x;
    private double y;


    public EnemyViewData(String enemy, double x, double y){
        this.enemy = enemy;
        this.x = x;
        this.y = y;

    }

    public String getEnemyName() {
        return enemy;
    }

    public double getEnemyX() {
        return x;
    }

    public double getEnemyY() {
        return y;
    }
}
