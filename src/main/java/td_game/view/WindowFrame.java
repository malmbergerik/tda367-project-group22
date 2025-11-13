package td_game.view;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private final int width = 1024;
    private final int height = 768;


    public WindowFrame() {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

}
