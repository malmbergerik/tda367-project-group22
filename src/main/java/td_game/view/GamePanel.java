package td_game.view;

import td_game.model.modelnit.GameModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameModel model;
    //private SideBarPanel sideBar;
    //private BottomBar bottomBar;

    public GamePanel(GameModel model) {

        setLayout(new BorderLayout());

        //Right side
        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(304, 768));
        sideBar.setBackground(Color.RED);
        add(sideBar, BorderLayout.EAST);

        //Left side
        JPanel leftside = new JPanel(new BorderLayout());
        add(leftside,BorderLayout.CENTER);

        GameViewPanel gameView = new GameViewPanel(model);
        leftside.add(gameView, BorderLayout.CENTER);



        JPanel bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(720, 192));
        bottomBar.setBackground(Color.BLUE);
        leftside.add(bottomBar, BorderLayout.SOUTH);





    }

}
