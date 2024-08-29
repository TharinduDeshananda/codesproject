package org.tdesh.screen;

import org.tdesh.panels.*;
import org.tdesh.util.MainWindowRoutes;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;

public class MainWindow extends JFrame {

    public static MainWindow instance;

    static {
        instance = new MainWindow();
    }

    private  MainWindow(){
        super("main-window");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e ){
            e.printStackTrace();

        }

        JFrame frame = new JFrame();
        this.setSize(1024,768);
        this.setTitle("Code Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        URL resource = ClassLoader.getSystemResource("images/noimg.png");
        ImageIcon topIcon = new ImageIcon(resource);
        this.setIconImage(topIcon.getImage());

        //set children
        this.add(new IndexPanel(), BorderLayout.CENTER);

        this.add(new LeftNavigatorPanel(),BorderLayout.WEST);
        this.setVisible(true);
    }

    public static MainWindow getInstance(){
        return MainWindow.instance;
    }

    public static void addNavigateItem(String item) {
        // Remove the existing component in the CENTER region
        Component[] components = instance.getContentPane().getComponents();
        for (Component component : components) {
            if (BorderLayout.CENTER.equals(((BorderLayout) instance.getContentPane().getLayout()).getConstraints(component))) {
                instance.remove(component);
            }
        }

        switch(item) {
            case MainWindowRoutes.BASE64_ROUTE:
                Base64Panel panel64 = new Base64Panel();
                instance.add(panel64, BorderLayout.CENTER);
                System.out.println("Base 64 route");
                break;
            case MainWindowRoutes.JWT_ROUTE:
                var panelJwt = new JWTPanel();
                instance.add(panelJwt, BorderLayout.CENTER);
                System.out.println("JWT route");
                break;
            case MainWindowRoutes.HASH_ROUTE:
                var panelHash = new HashPanel();
                instance.add(panelHash, BorderLayout.CENTER);
                System.out.println("hash route");
                break;
            default:
                return;
        }

        // Revalidate and repaint the frame to reflect changes
        instance.revalidate();
        instance.repaint();
    }
}
