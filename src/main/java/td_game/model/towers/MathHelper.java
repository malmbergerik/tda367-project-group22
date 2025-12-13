package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;

public class MathHelper {

    // Beräknar avstånd mellan två punkter
    public static double lenToTarget(ATower source, ABaseEnemy target) {
        double dx = target.getX() - source.getX();
        double dy = target.getY() - source.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    // Beräknar vinkel till målet i grader
    public static double getAngleToTarget(ATower source, ABaseEnemy target) {
        double dx = (target.getX() - target.getWidth() / 2.0) - source.getX();
        double dy = (target.getY() - target.getHeight() / 2.0) - source.getY();
        return Math.atan2(dy, dx)*(180/Math.PI);
    }
}
