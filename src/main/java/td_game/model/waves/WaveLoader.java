package td_game.model.waves;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Responsible for parsing wave configuration files and creating the
 * corresponding Wave objects.
 * AI was used to help write implement this class.
 */
public class WaveLoader {

    /**
     * Loads wave configurations from a specified file resource.
     * The file format is expected to be: ID | Type | Count | Interval | Delay.
     *
     * @param filename The path to the wave configuration file (relative to classpath).
     * @return A Queue containing the loaded Wave objects in sequential order.
     * @throws RuntimeException if the file cannot be found or an error occurs during loading.
     */
    public Queue<Wave> loadWaves(String filename) {
        // We use a Map temporarily to group lines by wave ID
        Map<Integer, Wave> waveMap = new TreeMap<>(); // TreeMap keeps keys sorted (Wave 1, 2, 3...)
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (is == null) {
                throw new RuntimeException("Wave configuration file not found: " + filename);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    // Skip comments (#) or empty lines
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue; // Skip empty lines and comments
                    }
                    // splits the string line into pieces wherever there is one or more whitespace characters
                    String[] parts = line.split("\\s+");
                    if (parts.length < 5) continue;

                    // Parse the line based on  format:
                    // ID | Type | Count | Interval | Delay
                    int waveId = Integer.parseInt(parts[0]);
                    String enemyType = parts[1];
                    int enemyCount = Integer.parseInt(parts[2]);
                    double spawnInterval = Double.parseDouble(parts[3]);
                    double initialDelay = Double.parseDouble(parts[4]);

                    // Get or Create Wave
                    waveMap.putIfAbsent(waveId, new Wave(waveId));
                    WaveGroup group = new WaveGroup(enemyType, enemyCount, spawnInterval, initialDelay);
                    waveMap.get(waveId).addGroup(group);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load waves");
        }

        return new LinkedList<>(waveMap.values());
    }
}