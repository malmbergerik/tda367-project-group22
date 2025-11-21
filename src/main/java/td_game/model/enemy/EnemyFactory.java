package td_game.model.enemy;

public class EnemyFactory {
    public static ABaseEnemy createEnemy(EnemyType enemy) {
        switch (enemy) {
            case skeleton:
                return new Skeleton();
            default:
                return null;
        }
    }
}