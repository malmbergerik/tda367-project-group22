package td_game.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class MenuPanel extends APanel {
    private JButton playButton;
    private JButton exitButton;

    private Image backgroundImage;
    private Image playButtonImage;
    private Image hoverPlayButtonImage;
    private Image exitButtonImage;
    private Image hoverExitButtonImage;

    private boolean playButtonPressed = false;

    public MenuPanel(int width, int height) {
        super(width, height);

        Dimension size = new Dimension(width, height);
        this.setSize(size);
        this.setLayout(new GridBagLayout());

        backgroundImage = loadImage("/assets/menu.png");
        playButtonImage = loadImage("/assets/play.png");
        hoverPlayButtonImage = loadImage("/assets/hoverPlay.png");
        exitButtonImage = loadImage("/assets/exit.png"); // Assuming this asset exists
        hoverExitButtonImage = loadImage("/assets/hoverExit.png");

        playButton = createButton(playButtonImage, hoverPlayButtonImage);
        exitButton = createButton(exitButtonImage, hoverExitButtonImage);

        this.playButton.addActionListener(e -> setPlayButtonPressed());
        this.exitButton.addActionListener(e -> System.exit(0));


        // Layout  setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 1;
        this.add(playButton, gbc);

        gbc.gridy = 2;
        this.add(exitButton, gbc);

    }

    private JButton createButton (Image staticImage, Image hoverImage){
        ImageIcon staticIcon = new ImageIcon(staticImage);
        ImageIcon hoverIcon = new ImageIcon(hoverImage);

        JButton button = new JButton(staticIcon);
        button.setRolloverIcon(hoverIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private Image loadImage (String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    protected boolean getPlayButtonPressed() {
        return playButtonPressed;
    }

    protected void setPlayButtonPressed() {
        this.playButtonPressed = true;
    }

    protected void resetPlayButtonPressed() {
        this.playButtonPressed = false;
    }
}
