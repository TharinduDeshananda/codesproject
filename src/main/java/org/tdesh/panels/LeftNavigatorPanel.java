package org.tdesh.panels;

import org.tdesh.components.SideNavButton;
import org.tdesh.screen.MainWindow;
import org.tdesh.util.MainWindowRoutes;
import org.tdesh.util.MyColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LeftNavigatorPanel extends JPanel {
    public LeftNavigatorPanel() {
        super();
        this.setBackground(MyColors.MAIN_BG_CL);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/noimg.png"));
        Image image = imageIcon.getImage();
        var scaledImage = image.getScaledInstance(100, 75, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(scaledImage));
        label.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Use GridLayout for equal width buttons
        Box box = new Box(BoxLayout.Y_AXIS);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align to the top left
        gbc.weighty = 1.0; // Push the components to the top
        this.add(box, gbc);

        box.add(label);

        // Add buttons
        SideNavButton base64Btn = new SideNavButton("Base 64", () -> {
            MainWindow.addNavigateItem(MainWindowRoutes.BASE64_ROUTE);
        });
        SideNavButton hashBtn = new SideNavButton("Hash", () -> {
            MainWindow.addNavigateItem(MainWindowRoutes.HASH_ROUTE);
        });
        SideNavButton jwtBtn = new SideNavButton("JWT", () -> {
            MainWindow.addNavigateItem(MainWindowRoutes.JWT_ROUTE);
        });

        // Set the layout to GridLayout to ensure equal width
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column, with a vertical gap of 10 pixels
        buttonPanel.setOpaque(false); // Make the panel transparent to match the background

        buttonPanel.add(base64Btn);
        buttonPanel.add(hashBtn);
        buttonPanel.add(jwtBtn);

        box.add(buttonPanel);

        box.setAlignmentX(Box.CENTER_ALIGNMENT);
    }
}
