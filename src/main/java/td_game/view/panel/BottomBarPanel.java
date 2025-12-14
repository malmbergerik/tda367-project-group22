package td_game.view.panel;

import td_game.view.helper.FontLoader;
import td_game.view.helper.TowerViewManager;
import td_game.view.listener.ITowerActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The bottom bar panel that displays selected tower information and sell options.
 */

public class BottomBarPanel extends JPanel {

    private ITowerActionListener listener;
    private TowerViewManager towerViewManager;
    private String selectedTower;
    private JLabel towerNameLabel;
    private JLabel towerImageLabel;
    private JButton sellButton;
    private JLabel sellTextLabel;
    private JLabel sellPriceLabel;
    private Font mainFont;

    public BottomBarPanel(int width, int height, TowerViewManager towerViewManager){
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridBagLayout());
        this.towerViewManager = towerViewManager;

        mainFont = FontLoader.loadFont("/assets/font/bleeding-pixels/BleedingPixels.ttf", 28f);

        JPanel infoPanel = createInfoPanel();

        // Sell button
        sellButton = createSellButton();
        sellButton.setPreferredSize(new Dimension(150, 50));
        sellButton.setMaximumSize(new Dimension(150, 50));
        sellButton.setMinimumSize(new Dimension(150, 50));

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        gbcInfo.anchor = GridBagConstraints.CENTER;
        add(infoPanel, gbcInfo);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 1;
        gbcButton.gridy = 0;
        gbcButton.insets = new Insets(0, 10, 0, 0);
        gbcButton.anchor = GridBagConstraints.CENTER;
        add(sellButton, gbcButton);

        deselectTower();
    }
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        towerImageLabel = new JLabel();
        towerImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        towerNameLabel = new JLabel();
        towerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        towerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(Box.createVerticalGlue());
        panel.add(towerImageLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(towerNameLabel);

        panel.add(Box.createVerticalGlue());
        return panel;
    }


    private JButton createSellButton() {
        sellButton = new JButton();
        sellButton.setLayout(new BoxLayout(sellButton, BoxLayout.X_AXIS));

        sellButton.setFont(mainFont);
        sellButton.setBackground(Color.decode("#333333"));
        sellButton.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        sellTextLabel = new JLabel("Sell");
        sellTextLabel.setForeground(Color.RED);
        sellTextLabel.setFont(mainFont);

        sellPriceLabel = new JLabel();
        sellPriceLabel.setForeground(Color.YELLOW);
        sellPriceLabel.setFont(mainFont);

        sellButton.add(Box.createHorizontalGlue());
        sellButton.add(sellTextLabel);
        sellButton.add(Box.createHorizontalStrut(5)); // gap between labels
        sellButton.add(sellPriceLabel);
        sellButton.add(Box.createHorizontalGlue());


        sellButton.addActionListener(e -> {
            if (listener != null && selectedTower != null) {
                listener.onTowerSell();
                deselectTower();
            }
        });

        sellButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sellButton.setBackground(Color.decode("#555555")); // hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sellButton.setBackground(Color.decode("#333333")); // reset
            }
        });

        return sellButton;
    }
    private void deselectTower(){
        selectedTower = null;
        sellButton.setVisible(false);
        sellPriceLabel.setText("");

        towerImageLabel.setVisible(false);
        towerNameLabel.setVisible(false);
    }
    private void selectTower(String tower){
        selectedTower = tower;
        sellButton.setVisible(true);
        towerImageLabel.setVisible(true);
        towerNameLabel.setVisible(true);

    }
    public void hideTowerInformation(){
        deselectTower();
    }
    public void showTowerInformation(String tower, int sellprice){

        selectTower(tower);
        towerNameLabel.setText(tower);
        towerNameLabel.setForeground(Color.decode("#FF5555"));
        sellPriceLabel.setText(":   " + sellprice);

        BufferedImage towerImage = towerViewManager.getTowerImage(tower);
        if (towerImage != null) {
            Image scaledImage = towerImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            towerImageLabel.setIcon(new ImageIcon(scaledImage));
        }

        revalidate();
        repaint();
    }



    public void setListener(ITowerActionListener listener){
        this.listener = listener;
    }

}
