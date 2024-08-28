package org.tdesh.panels;

import org.tdesh.components.SideNavButton;
import org.tdesh.screen.MainWindow;
import org.tdesh.util.MainWindowRoutes;
import org.tdesh.util.MyColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LeftNavigatorPanel extends JPanel {
    public LeftNavigatorPanel(){
        super();
        this.setBackground(MyColors.MAIN_BG_CL);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/noimg.png"));
        Image image = imageIcon.getImage();
        var scaledImage = image.getScaledInstance(100, 75, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(scaledImage));
        label.setBorder(new EmptyBorder(20, 30, 20, 30));
        Box box = new Box(BoxLayout.Y_AXIS);
        this.add(box);

        box.add(label);

        //add buttons
        SideNavButton base64Btn = new SideNavButton("Base 64",()->{
            MainWindow.addNavigateItem(MainWindowRoutes.BASE64_ROUTE);
        });
        box.add(base64Btn);

        SideNavButton hashBtn = new SideNavButton("Hash",()->{
            MainWindow.addNavigateItem(MainWindowRoutes.HASH_ROUTE);
        });
        box.add(hashBtn);

        SideNavButton jwtBtn = new SideNavButton("JWT",()->{
            MainWindow.addNavigateItem(MainWindowRoutes.JWT_ROUTE);
        });
        box.add(jwtBtn);

        box.setAlignmentY(Box.CENTER_ALIGNMENT);

    }
}
