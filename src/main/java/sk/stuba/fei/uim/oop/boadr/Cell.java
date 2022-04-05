package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static sk.stuba.fei.uim.oop.boadr.TokenColor.NOT_SPECIFIED;

public class Cell extends JPanel implements MouseListener {

    private Dimension preferredSize;

    private Border highlightedBorder = BorderFactory.createLineBorder(Color.cyan,2);
    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
    private int positionX;
    private int positionY;
    private Color cellColor;
    private Color selectedColor;
    @Getter
    private Color highlightedColor = Color.red;
//    private boolean isEmpty;
    @Getter
    @Setter
    private boolean isHighlighted;
    @Getter
    @Setter
    private boolean isPossibleToPaint;

    @Getter
    @Setter
    private TokenColor tokenColor;


    public Cell(int y, int x, Dimension preferredSize) {
//        cellColor = (x + y) % 2 == 0 ? Color.GRAY : Color.DARK_GRAY;
        this.preferredSize = preferredSize;
        this.positionX = x;
        this.positionY = y;
        setBackground(Color.gray);
        setBorder(blackBorder);
        addMouseListener(this);
        setFocusable(true);
        isPossibleToPaint = false;
        tokenColor = NOT_SPECIFIED;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        setHighlighted(true);
        if (isHighlighted()) {
            isPossibleToPaint = true;
        }
    }

//    public void paint(Graphics g){
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        if (isHighlighted) {
//            g2d.setColor(Color.yellow);
//            g2d.setStroke(new BasicStroke(4));
//            g2d.drawRect(0, 0, getWidth(), getHeight());
//        }
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.yellow);
//        g.fillOval(0, 0, 20, 20);
//
        Graphics2D g2d = (Graphics2D) g.create();
        if (getTokenColor() == TokenColor.BLACK) {
            g2d.setColor(Color.black);
            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
        }
        if (getTokenColor() == TokenColor.WHITE) {
            g2d.setColor(Color.white);
            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
        }
//        if (isHighlighted) {
//            g2d.setColor(Color.yellow);
//            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
////            g2d.setStroke(new BasicStroke(4));
////            g2d.drawRect(0, 0, getWidth(), getHeight());
//        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        setBorder(highlightedBorder);
        if (isHighlighted) {
            setBorder(highlightedBorder);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(blackBorder);
    }

}
