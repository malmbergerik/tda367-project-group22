package td_game.model.waves;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private final int waveNumber;
    private final List<WaveGroup> waveGroups;

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.waveGroups = new ArrayList<>();
    }

    public void addWaveGroup(WaveGroup group) {
        waveGroups.add(group);
    }

    public List<WaveGroup> getWaveGroups() {
        return waveGroups;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
