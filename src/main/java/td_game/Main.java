package td_game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import td_game.model.map.GrassTile;
import td_game.model.map.PathTile;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater (() -> {
            JFrame window = new JFrame();

            PathTile path = new PathTile(10,10);
            System.out.println(path.getX());
            System.out.println(path.getY());
            System.out.println(path.getType());

            GrassTile grass = new GrassTile(5,5);
            System.out.println(grass.getX());
            System.out.println(grass.getY());
            System.out.println(grass.getType());

            window.setTitle("Tower defence game");

            window.setSize(1200, 800);

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            window.setLocationRelativeTo(null);

            window.setVisible(true);
        });
    }
}