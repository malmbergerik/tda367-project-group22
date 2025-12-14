package td_game.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * An abstract base class for overlay panels with a semi-transparent background.
 */

public class ABaseOverlayPanel extends JPanel {
    protected GridBagConstraints gbc;

    public ABaseOverlayPanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);

        // Mousetrap
        addMouseListener(new MouseAdapter() {});

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
    }

    protected void addTitle(String text, Color color) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(color);
        add(titleLabel, gbc);
        gbc.gridy++;
    }

    protected JButton addButton(String text, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 20));
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);

        gbc.insets = new Insets(10, 0, 10, 0);
        add(btn, gbc);
        gbc.gridy++;
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
