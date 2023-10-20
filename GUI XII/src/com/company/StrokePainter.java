package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StrokePainter extends JFrame {

    public StrokePainter() {

        this.setTitle("GUI XII - zadanie 2");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(new StrokePanel());
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}

class StrokePanel extends JPanel {

    private int x1, y1, x2, y2;
    private Color color;

    public StrokePanel() {
        super();
        this.addMouseListener(new PaintingPanelMouseListener());
        this.addMouseMotionListener(new PaintingPanelMouseListener());
        this.setSize(600, 300);
        this.setBackground(Color.white);
        this.color = Color.red;
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(color);

        g2d.drawLine(x1, y1, x2, y2);

    }

    class PaintingPanelMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }
    }
}
