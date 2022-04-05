package sk.stuba.fei.uim.oop.boadr;

import javax.swing.*;
import java.awt.*;

public class Token extends JComponent {
    private Color color;
    private int x;
    private int y;
    private int diameter;



    public Token(Color color, Dimension preferredSize, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        setBackground(Color.yellow);
//        setPreferredSize(preferredSize);
        setLocation(5, 5);
        setSize(10, 10);
    }

//    public void paintComponent(final Graphics g) {
//        // TODO print tokent
//        super.paintComponent(g);
//        g.setColor(Color.white);
//        g.fillOval(0, 0, 50, 50);
//    }

//    @Override
//    public void paint(Graphics g)
//    {
//        super.paint(g);
//// setting the color of circle
//        g.setColor(Color.yellow);
//        g.fillOval(0, 0, 80, 80);
//    }
}
