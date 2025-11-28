package td_game.model.enemy;

import td_game.model.GameEventType;
import td_game.model.modelnit.GameModel;
import td_game.model.enemy.ABaseEnemy;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    private EnemyWaveSpawner waveSpawner;
    public GameModel gameModel;

    public EnemyManager(GameModel gameModel) {
        this.waveSpawner = new EnemyWaveSpawner(this);
        this.gameModel = gameModel;
        this.activeEnemies = gameModel.getActiveEnemies();
        startNextWave();
    }

    public void update() {

        waveSpawner.update();
        activeEnemies.removeIf( enemy -> {
            enemy.move();
            return (!enemy.isAlive() || enemy.hasReachedEnd());
        });
        gameModel.notifyObserver(GameEventType.MOVING_OBJECTS_UPDATE);
    }

    public void startNextWave(){
        waveSpawner.startNextWave();
    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }

    public List<ABaseEnemy> getActiveEnemies() {
        return activeEnemies;
    }

    public void reset() {
        activeEnemies.clear();
        waveSpawner.reset();
    }


}
