package td_game.model.enemy;

public class EnemyFactory {
    public static ABaseEnemy createEnemy(EnemyType enemy) {
        switch (enemy) {
            case enemy1:
                return new Enemy1();
            default:
                return null;
        }
    }
}