package td_game.model.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testPlayerDamageable(){
        Player player = new Player(new PlayerHealth(100));
        player.takeDamage(50);
        assertEquals(50,player.getHealth());
    }
    

}