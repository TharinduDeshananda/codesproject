package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;

public class JWTPanel extends JPanel {
    public  JWTPanel(){
        super();
        JLabel simpleLabel = new JLabel("JWT Panel");

        this.add(simpleLabel);
        this.setBackground(MyColors.MAIN_BG_CL);
        this.setVisible(true);
    }
}
