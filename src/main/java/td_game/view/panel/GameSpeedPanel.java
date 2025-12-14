package td_game.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameSpeedPanel extends JPanel {

    private JLabel waveLabel;
    private JButton startWaveButton;

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
        if (startWaveButton == null) return;
        startWaveButton.setEnabled(enabled);
    }

    public void addStartWaveListener(ActionListener listener) {
        startWaveButton.addActionListener(listener);
    }
}
