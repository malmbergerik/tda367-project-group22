package td_game.model.enemy;

import td_game.model.events.MovingObjectUpdateEvent;
import td_game.model.modelnit.GameModel;
import td_game.model.waves.WaveManager;

import java.util.List;

public class EnemyManager {

    private List<ABaseEnemy> activeEnemies;
    private WaveManager waveManager;
    public GameModel gameModel;

    public EnemyManager(GameModel gameModel, EnemyFactory enemyFactory) {
        this.waveManager = new WaveManager(this, enemyFactory);
        this.gameModel = gameModel;
        this.activeEnemies = gameModel.getActiveEnemies();
        startNextWave();
    }

    public void update() {
        waveManager.update();
        activeEnemies.removeIf( enemy -> {
            enemy.move();
            return (!enemy.isAlive() || enemy.hasReachedEnd());
        });
        gameModel.notifyObserver(new MovingObjectUpdateEvent());

        if(activeEnemies.isEmpty() && waveManager.isWaveComplete()){
            startNextWave();
        }
    }

    public void startNextWave(){
        waveManager.startNextWave();
    }

    public void addEnemy(ABaseEnemy enemy) {
        activeEnemies.add(enemy);
    }

    public List<ABaseEnemy> getActiveEnemies() {
        return activeEnemies;
    }

    public void reset() {
        activeEnemies.clear();
        waveManager.reset();
    }


}
