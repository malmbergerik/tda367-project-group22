package td_game.model.waves;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single wave in the game, consisting of a sequence of wave groups.
 */
public class Wave {
    private final int waveNumber;
    private final List<WaveGroup> waveGroups;

    /**
     * Constructs a new Wave with a specific identification number.
     *
     * @param waveNumber The sequential number of this wave.
     */
    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.waveGroups = new ArrayList<>();
    }

    /**
     * Adds a group of enemies to this wave configuration.
     *
     * @param group The WaveGroup to add.
     */
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