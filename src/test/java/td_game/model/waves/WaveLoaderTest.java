package td_game.model.waves;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WaveLoaderTest {
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
                        continue;
                    }

                    // splits the string line into pieces wherever there is one or more whitespace characters
                    String[] parts = line.split("\\s+");

                    // VALIDATION: Ensure the line has enough parts to parse
                    if (parts.length < 5) {
                        System.err.println("Skipping malformed line in waves file: " + line);
                        continue;
                    }

                    // Parse the line based on format:
                    // ID | Type | Count | Interval | Delay
                    try {
                        int waveId = Integer.parseInt(parts[0]);
                        String enemyType = parts[1];
                        int enemyCount = Integer.parseInt(parts[2]);
                        double spawnInterval = Double.parseDouble(parts[3]);
                        double initialDelay = Double.parseDouble(parts[4]);

                        // Get or Create Wave
                        waveMap.putIfAbsent(waveId, new Wave(waveId));
                        WaveGroup group = new WaveGroup(enemyType, enemyCount, spawnInterval, initialDelay);
                        waveMap.get(waveId).addGroup(group);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number in line: " + line);
                    }
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