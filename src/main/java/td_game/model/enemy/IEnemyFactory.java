package td_game.model.enemy;

import td_game.model.path.Path;

public interface IEnemyFactory {
    ABaseEnemy createEnemy(Path path);
}