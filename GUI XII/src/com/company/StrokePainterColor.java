package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StrokePainterColor extends JFrame {

    public StrokePainterColor() {

        this.setTitle("GUI XII - zadanie 3");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(new StrokePanelColor());
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}

class StrokePanelColor extends JPanel {

    private int x1, y1, x2, y2;
    private Color color;

    public StrokePanelColor() {

        this.addKeyListener(new PaintingPanelKeyAdapter());

        this.addMouseListener(new PaintingPanelMouseAdapter());
        this.addMouseMotionListener(new PaintingPanelMouseAdapter());

        this.setSize(600, 300);
        this.setFocusable(true);
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

    class PaintingPanelKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F1 -> color = Color.red;
                case KeyEvent.VK_F2 -> color = Color.blue;
                case KeyEvent.VK_F3 -> color = Color.green;
                case KeyEvent.VK_F4 -> color = Color.magenta;
                case KeyEvent.VK_F5 -> color = Color.orange;
                case KeyEvent.VK_F6 -> color = Color.pink;
            }
        }
    }

    class PaintingPanelMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }
    }
}
