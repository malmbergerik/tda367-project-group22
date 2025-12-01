package td_game.view.panel;

import td_game.view.listener.IMenuView;
import td_game.view.IView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class MenuPanel extends APanel implements IMenuView, IView {
    private final JButton playButton;
    private final JButton exitButton;
    private final Image backgroundImage;

    public MenuPanel(int width, int height) {
        super(width, height);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setLayout(new GridBagLayout());

        backgroundImage = loadImage("/assets/menu.png");
        Image playButtonImage = loadImage("/assets/play.png");
        Image hoverPlayButtonImage = loadImage("/assets/hoverPlay.png");
        Image exitButtonImage = loadImage("/assets/exit.png");
        Image hoverExitButtonImage = loadImage("/assets/hoverExit.png");

        playButton = createButton(playButtonImage, hoverPlayButtonImage);
        exitButton = createButton(exitButtonImage, hoverExitButtonImage);

        addComponent(playButton,0);
        addComponent(exitButton,1);

    }

    public void addPlayListener(ActionListener listener) {
        playButton.addActionListener(listener);
    }

    public  void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
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

    private void addComponent(JComponent component, int gridY){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(component, gbc);
    }

    @Override
    public JPanel getViewPanel() {
        return this;
    }
}
