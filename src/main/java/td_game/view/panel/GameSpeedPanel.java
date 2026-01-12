package td_game.view.panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * The panel that displays the current wave and a button to start the wave.
 */

public class GameSpeedPanel extends JPanel {

    private JLabel waveLabel;
    private JButton startWaveButton;
    private JButton pauseButton;
    public GameSpeedPanel(int width, int height){

        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));

        this.setBackground(Color.decode("#222222"));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60));

        waveLabel = new JLabel();
        waveLabel.setForeground(Color.YELLOW);
        waveLabel.setFont(new Font("Arial", Font.BOLD, 16));



        startWaveButton = new JButton("Start Wave");
        startWaveButton.setPreferredSize(new Dimension(120, 40));
        startWaveButton.setFocusable(false);
        startWaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        pauseButton = new JButton();
        pauseButton.setPreferredSize(new Dimension(48,48));
        pauseButton.setFocusable(false);
        pauseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPauseButtonImage(pauseButton);

        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);

        add(waveLabel);
        add(startWaveButton);
        add(pauseButton);
    }

    private void setPauseButtonImage(JButton button){
        try (InputStream is = getClass().getResourceAsStream("/assets/pause.png")) {
            if (is == null) {
                System.out.println("Image not found!");
            } else {
                Image img = ImageIO.read(is);
                button.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Update the wave label with the current wave number
     * @param currentWave the current wave number
     */
    public void updateWave(int currentWave) {
        waveLabel.setText(currentWave + " / " + "10");
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

    public void addPaueButtonListener(ActionListener listener){pauseButton.addActionListener(listener);}
}
