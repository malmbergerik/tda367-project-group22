package td_game.view.panel;

import td_game.view.helper.FontLoader;

import javax.swing.*;
import java.awt.*;

/**
 * The stats panel that displays the player's health and money.
 */

public class StatsPanel extends JPanel {
    private final JLabel healthLabel;
    private final JLabel moneyLabel;
    private final int maxHealth;

    private Font bleedingPixels;

    public StatsPanel(int width, int height, int maxHealth) {

        bleedingPixels = FontLoader.loadFont("/assets/font/bleeding-pixels/BleedingPixels.ttf", 28f);
        this.maxHealth = maxHealth;


        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#181818"));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 5, 0, 0);

        healthLabel = new JLabel();
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(bleedingPixels);
        this.add(healthLabel, gbc);

        gbc.gridy = 1;

        moneyLabel = new JLabel();
        moneyLabel.setForeground(Color.YELLOW);
        moneyLabel.setFont(bleedingPixels);


        this.add(moneyLabel, gbc);
    }

    /** Update the health label with the current health
     * @param currentHealth the current health
     */
    public void updateHealth(int currentHealth) {
        healthLabel.setText("HP:           " + currentHealth + " / " + maxHealth);
    }

    /** Update the money label with the current money
     * @param currentMoney the current money
     */
    public void updateMoney(int currentMoney) {
        moneyLabel.setText("Money:     " + currentMoney);
    }
}
