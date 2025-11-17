package td_game.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class MenuPanel extends JPanel {
    private JButton playButton;
    private Image backgroundImage;
    private Image playButtonImage;
    private Image hoverButtonImage;

    public MenuPanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1024, 768));

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        
        try {

            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/assets/menu.png"));
            playButtonImage = ImageIO.read(getClass().getResourceAsStream("/assets/play.png"));
            hoverButtonImage = ImageIO.read(getClass().getResourceAsStream("/assets/hoverPlay.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon staticIcon = new ImageIcon(playButtonImage);
        ImageIcon hoverIcon = new ImageIcon(hoverButtonImage);

        playButton = new JButton(staticIcon);
        playButton.setRolloverIcon(hoverIcon);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);

        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(playButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addPlayButtonListener(ActionListener listener) {
        playButton.addActionListener(listener);
    }

}
