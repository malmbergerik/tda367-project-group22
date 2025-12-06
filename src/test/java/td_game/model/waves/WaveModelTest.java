package td_game.model.waves;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaveModelTest {

    @Test
    void testWaveGroupProperties() {
        // Skapa en WaveGroup med specifika värden
        // Typ: "Skeleton", Antal: 10, Intervall: 1.5 sekunder, Delay: 2.0 sekunder
        WaveGroup group = new WaveGroup("Skeleton", 10, 1.5, 2.0);

        // Verifiera att getters returnerar exakt det vi stoppade in
        assertEquals("Skeleton", group.getEnemyType(), "Fiendetypen ska vara Skeleton");
        assertEquals(10, group.getCount(), "Antalet fiender ska vara 10");
        assertEquals(1.5, group.getSpawnInterval(), 0.0001, "Spawn-intervallet ska vara 1.5");
        assertEquals(2.0, group.getInitialDelay(), 0.0001, "Initial delay ska vara 2.0");
    }

    @Test
    void testWaveStructure() {
        // Skapa en Våg med nummer 5
        Wave wave = new Wave(5);

        assertEquals(5, wave.getWaveNumber(), "Vågnumret ska vara 5");
        assertTrue(wave.getGroups().isEmpty(), "En ny våg ska inte ha några grupper initialt");

        // Skapa och lägg till en grupp
        WaveGroup group1 = new WaveGroup("Slime", 5, 1.0, 0.0);
        wave.addGroup(group1);

        assertEquals(1, wave.getGroups().size(), "Vågen ska ha 1 grupp efter tillägg");
        assertEquals(group1, wave.getGroups().get(0), "Den tillagda gruppen ska gå att hämta");

        // Lägg till en andra grupp
        WaveGroup group2 = new WaveGroup("Bat", 3, 0.5, 5.0);
        wave.addGroup(group2);

        assertEquals(2, wave.getGroups().size(), "Vågen ska nu ha 2 grupper");
        assertEquals(group2, wave.getGroups().get(1), "Andra gruppen ska ligga på index 1");
    }
}