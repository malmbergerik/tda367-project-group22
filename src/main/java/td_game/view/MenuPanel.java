package td_game.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MenuPanel extends JPanel {
    private JButton playButton;
    private Image backgroundImage;
    private Image playButtonImage;

    public MenuPanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1024, 768));

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        
        try {

            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/assets/menu.png"));
            //InputStream buttomStream = classLoader.getResourceAsStream("assets/grass.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playButton = new JButton();
        playButton.setSize(200, 100);
        playButton.setBackground(Color.BLUE);

        add(playButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
