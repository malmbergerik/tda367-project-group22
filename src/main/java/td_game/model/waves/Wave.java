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

    public void addGroup(WaveGroup group) {
        waveGroups.add(group);
    }

    public List<WaveGroup> getGroups() {
        return waveGroups;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
