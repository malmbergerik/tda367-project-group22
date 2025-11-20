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
        JPanel leftSideBar = new JPanel(new GridBagLayout());
        add(leftSideBar,BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx= 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;

        GameViewPanel gameView = new GameViewPanel(model);
        gameView.setPreferredSize(new Dimension(720, 576));

        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;

        leftSideBar.add(gameView, gbc);


        JPanel bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(720, 192));
        bottomBar.setBackground(Color.BLUE);

        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        leftSideBar.add(bottomBar, gbc);





    }

}
