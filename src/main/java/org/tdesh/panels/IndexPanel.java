package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IndexPanel extends JPanel {

    public IndexPanel() {
        super(new BorderLayout());
        this.setBackground(MyColors.MAIN_BG_CL);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Developer Utility Hub!", JLabel.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

        // Description Area
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("This application is a collection of essential tools designed to simplify and "
                + "enhance your day-to-day development tasks. Whether you need to encode/decode Base64, generate and validate "
                + "hashes, handle JWTs, or perform other common tasks, this hub has got you covered.\n\n"
                + "Explore the utilities using the navigation panel on the left.");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setForeground(MyColors.MAIN_TEXT_CL);
        descriptionArea.setBackground(MyColors.MAIN_BG_CL);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
//        descriptionArea.setBorder(BorderFactory.createCompoundBorder(
//                new EmptyBorder(10, 20, 10, 20),
//                BorderFactory.createLineBorder(MyColors.MAIN_TEXT_CL, 1)
//        ));
        descriptionArea.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Adding components to the panel
        this.add(welcomeLabel, BorderLayout.NORTH);
        this.add(descriptionArea, BorderLayout.CENTER);
    }
}
