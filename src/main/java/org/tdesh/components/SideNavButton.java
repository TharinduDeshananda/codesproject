package org.tdesh.components;

import org.tdesh.util.SimpleExecute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.function.Function;

public class SideNavButton extends JButton {

    private final SimpleExecute onNavigate;

    private Color defaultBackgroundColor = new Color(123, 123, 220);
    private Color hoverBackgroundColor = defaultBackgroundColor.brighter();
    private Color pressedBackgroundColor = defaultBackgroundColor.darker();
    private Color textColor = Color.WHITE;

    public SideNavButton(String text,SimpleExecute onNavigate) {
        super(text);
        this.onNavigate = onNavigate;
        initUI();
    }



    private void initUI() {
        // Set background color
        setBackground(defaultBackgroundColor);
        setForeground(textColor);

        // Set font
        setFont(new Font("Arial", Font.BOLD, 12));

        // Set padding
        setMargin(new Insets(10, 20, 10, 20));

        // Remove button border and focus
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        // Set cursor to hand cursor when hovering
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add mouse listener for hover and press effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackgroundColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedBackgroundColor);
                onNavigate.execute();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(defaultBackgroundColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Enable antialiasing for smoother rounded edges
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 30px arc width and height for rounding

        // Paint the text and other components
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Optionally paint a border (if desired) or leave empty for no border
    }

}

