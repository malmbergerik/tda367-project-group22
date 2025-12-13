package td_game.view.panel;

import javax.swing.*;
import java.awt.*;

public class GameSpeedPanel extends JPanel {

    private JLabel waveLabel;

    public GameSpeedPanel(int width, int height){

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#222222"));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));


        waveLabel = new JLabel();
        waveLabel.setForeground(Color.YELLOW);
        waveLabel.setFont(new Font("Arial", Font.BOLD, 16));



        startWaveButton = new JButton("Start Wave");
        startWaveButton.setPreferredSize(new Dimension(120, 40));
        startWaveButton.setFocusable(false);
        startWaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(waveLabel);
        add(startWaveButton);
    }

    public void updateWave(int currentWave) {
        waveLabel.setText(currentWave + " / " + "15");
    }

    public void setStartWaveButtonEnabled(boolean enabled) {
        startWaveButton.setEnabled(enabled);
        if(enabled) {
            startWaveButton.setText("Start Wave");
        } else {
            startWaveButton.setText("In Progress");
        }
    }

    public void addStartWaveListener(ActionListener listener) {
        startWaveButton.addActionListener(listener);
    }
}
