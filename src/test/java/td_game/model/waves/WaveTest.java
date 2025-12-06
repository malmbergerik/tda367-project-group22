package td_game.model.waves;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class WaveTest {

    @Test
    void testWaveGroupProperties() {
        // Create a WaveGroup with specific parameters
        // Type: "Skeleton", Count: 5, Interval: 1.0, Delay: 2.0
        WaveGroup group = new WaveGroup("Skeleton", 5, 1.0, 2.0);

        assertEquals("Skeleton", group.getEnemyType(), "Enemy type should be Skeleton");
        assertEquals(5, group.getCount(), "Enemy count should be 5");
        assertEquals(1.0, group.getSpawnInterval(), 0.0001, "Spawn interval should be 1.0");
        assertEquals(2.0, group.getInitialDelay(), 0.0001, "Initial delay should be 2.0");
    }

    @Test
    void testWaveStructure() {
        // Create a Wave with ID 1
        Wave wave = new Wave(1);

        assertEquals(1, wave.getWaveNumber(), "Wave number should be 1");
        assertTrue(wave.getGroups().isEmpty(), "New wave should have no groups initially");

        // Add a group
        WaveGroup group = new WaveGroup("Slime", 10, 0.5, 0.0);
        wave.addGroup(group);

        assertEquals(1, wave.getGroups().size(), "Wave should have 1 group after adding one");
        assertEquals(group, wave.getGroups().get(0), "The added group should be retrievable");
    }

    @Test
    void testWaveLoader() {
        // This test relies on the "waves/waves.txt" file present in the resources
        WaveLoader loader = new WaveLoader();
        Queue<Wave> waves = loader.loadWaves("waves/waves.txt");

        assertNotNull(waves, "Wave queue should not be null");
        assertFalse(waves.isEmpty(), "Wave queue should not be empty");

        // Based on the known content of waves.txt:
        // Wave 1: 1 Skeleton 10 0.5 0

        Wave firstWave = waves.peek(); // Peek at the first wave
        assertNotNull(firstWave);
        assertEquals(1, firstWave.getWaveNumber());

        List<WaveGroup> groups = firstWave.getGroups();
        assertEquals(1, groups.size(), "Wave 1 should have exactly 1 group");

        WaveGroup firstGroup = groups.get(0);
        assertEquals("Skeleton", firstGroup.getEnemyType());
        assertEquals(10, firstGroup.getCount());
        assertEquals(0.5, firstGroup.getSpawnInterval(), 0.0001);
        assertEquals(0.0, firstGroup.getInitialDelay(), 0.0001);
    }
}