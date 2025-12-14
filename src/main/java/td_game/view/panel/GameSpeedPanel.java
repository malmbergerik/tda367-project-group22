package td_game.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The panel that displays the current wave and a button to start the wave.
 */

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


    /** Update the wave label with the current wave number
     * @param currentWave the current wave number
     */
    public void updateWave(int currentWave) {
        waveLabel.setText(currentWave + " / " + "15");
    }

    /** Enable or disable the start wave button
     * @param enabled true to enable, false to disable
     */
    public void setStartWaveButtonEnabled(boolean enabled) {
        if (startWaveButton == null) return;
        startWaveButton.setEnabled(enabled);
    }

    // Add an ActionListener to the start wave button
    public void addStartWaveListener(ActionListener listener) {
        startWaveButton.addActionListener(listener);
    }
}
