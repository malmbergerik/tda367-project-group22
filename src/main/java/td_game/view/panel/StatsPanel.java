package td_game.view.panel;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private final JProgressBar healthBar;
    private final JLabel moneyLabel;
    private final int maxHealth;

    public StatsPanel(int width, int height, int maxHealth) {
        this.maxHealth = maxHealth;

        this.setPreferredSize(new Dimension(width, height));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        healthBar = new JProgressBar(0, maxHealth);
        healthBar.setValue(maxHealth);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.DARK_GRAY);
        healthBar.setPreferredSize(new Dimension(200, 30));

        moneyLabel = new JLabel();
        moneyLabel.setForeground(Color.YELLOW);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 16));

        this.add(new JLabel("HP:") {{
            setForeground(Color.DARK_GRAY);
        }});
        this.add(healthBar);
        this.add(moneyLabel);
    }

    public void updateHealth(int currentHealth) {
        healthBar.setValue(currentHealth);
        healthBar.setString(currentHealth + " / " + maxHealth);
    }

    public void updateMoney(int currentMoney) {
        moneyLabel.setText("Money: " + currentMoney);
    }
}
