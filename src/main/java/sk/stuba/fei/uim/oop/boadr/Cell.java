package sk.stuba.fei.uim.oop.boadr;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Cell extends JPanel implements MouseListener {

    private Dimension preferredSize;

    private Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
    private int positionX;
    private int positionY;
    private Color cellColor;
    private Color selectedColor;
    private boolean isEmpty;
    private boolean isHighlighted;

    public Cell(int y, int x, Dimension preferredSize) {
//        cellColor = (x + y) % 2 == 0 ? Color.GRAY : Color.DARK_GRAY;
        this.preferredSize = preferredSize;
        setBackground(Color.gray);
        setBorder(blackBorder);
        addMouseListener(this);
        setFocusable(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        var newPoint
//        points.add(new Point(e.getX(), e.getY()));
        setBackground(Color.black);

//        add()
//        repaint();

    }
//
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(Color.red);
        g.setColor(Color.BLACK);
        g.fillOval(5+2 * 60, 5+2 * 60, 50, 50);




    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(redBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(blackBorder);
    }
}
