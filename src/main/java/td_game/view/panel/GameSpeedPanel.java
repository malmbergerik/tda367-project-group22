package td_game.view.panel;

import javax.swing.*;
import java.awt.*;

public class GameSpeedPanel extends JPanel {

    private JLabel waveLabel;

    public GameSpeedPanel(int width, int height){

        this.setPreferredSize(new Dimension(width, height));


        waveLabel = new JLabel();
        waveLabel.setForeground(Color.YELLOW);
        waveLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(96,96));
        button.setMaximumSize(new Dimension(96,96));
        button.setMinimumSize(new Dimension(96,96));

        add(waveLabel);
        add(button);
    }

    public void updateWave(int currentWave) {
        waveLabel.setText(currentWave + " / " + "10");
    }
}
