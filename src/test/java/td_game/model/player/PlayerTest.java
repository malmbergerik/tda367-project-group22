package td_game.model.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testPlayerDamageable(){
        Player player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        player.takeDamage(50);
        assertEquals(50,player.getHealth());
    }

    @Test
    void testMoneyEarned(){
        Player player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        player.addMoney(20);
        assertEquals(120,player.getMoney());
    }

    @Test
    void testMoneySpent(){
        Player player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        player.spendMoney(20);
        assertEquals(80,player.getMoney());

    }
    

}