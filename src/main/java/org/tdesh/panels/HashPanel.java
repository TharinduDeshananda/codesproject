package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;

public class HashPanel extends JPanel {

    public HashPanel(){
        super();
        JLabel simpleLabel = new JLabel("Hash panel");

        this.add(simpleLabel);
        this.setBackground(MyColors.MAIN_BG_CL);
        this.setVisible(true);
    }
}
