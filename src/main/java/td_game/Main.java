package td_game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater (() -> {
            JFrame window = new JFrame();

            window.setTitle("Tower defence game");

            window.setSize(1200, 800);

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            window.setLocationRelativeTo(null);

            window.setVisible(true);
        });
    }
}