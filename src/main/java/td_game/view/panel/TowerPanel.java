package td_game.view.panel;

import td_game.view.listener.ITowerPlacementListener;
import td_game.view.helper.TowerViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TowerPanel extends JPanel {
    private ITowerPlacementListener listener;
    private int row = 0;
    private int col = 0;

    private final TowerViewManager towerViewManager;

    public TowerPanel(int width, int height, TowerViewManager towerViewManager){
        this.towerViewManager = towerViewManager;

        setPreferredSize(new Dimension(width,height));

        setBackground(Color.decode("#181818"));

        setLayout(new GridBagLayout());

        addToPanel(createTowerButton("CanonTower"));
        addToPanel(createTowerButton("SniperTower"));
        addToPanel(createTowerButton("FlameThrowerTower"));

        fillPanel();

    }

    private JButton createTowerButton(String name){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(96,96));
        button.setMaximumSize(new Dimension(96,96));
        button.setMinimumSize(new Dimension(96,96));

        if( towerViewManager.getTowerImage(name) != null) {
            BufferedImage towerImage = towerViewManager.getTowerImage(name);
            Image scaledImage = towerImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            button.setIcon(icon);
        }
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(e ->{
            if(listener != null) {
                listener.onTowerSelection(name);
            }
        });

        button.setFocusable(false);
        return button;
    }
    private void addToPanel(JButton button){
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,10,10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.NORTH;

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.weighty = 0;
            gbc.weightx = 0;

            add(button,gbc);

            col++;
            if(col>= 2){
                col = 0;
                row++;
            }

    }

    private void fillPanel(){
        GridBagConstraints filler = new GridBagConstraints();
        filler.gridx = 0;
        filler.gridy = row;
        filler.gridwidth = 2;
        filler.weighty = 1;
        filler.fill = GridBagConstraints.VERTICAL;
        add(Box.createVerticalGlue(),filler);
    }
    public void setListener(ITowerPlacementListener listener){
        this.listener = listener;
    }

}
