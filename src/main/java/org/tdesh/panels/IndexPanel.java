package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;
import java.awt.*;

public class IndexPanel extends JPanel {

    public IndexPanel(){

        JLabel simpleLabel = new JLabel("Hello Tharindu");

        this.add(simpleLabel);
        this.setBackground(MyColors.MAIN_BG_CL);
        this.setVisible(true);
    }
}
