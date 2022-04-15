package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.controls.CellHandler;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import static sk.stuba.fei.uim.oop.boadr.TokenColor.NOT_SPECIFIED;

public class Cell extends JPanel implements MouseListener, Painter {

    private Dimension preferredSize;

    private Border highlightedBorder = BorderFactory.createLineBorder(Color.cyan,2);
    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
    private Border possibleBorder = BorderFactory.createLineBorder(Color.WHITE);
    @Getter
    @Setter
    private int positionX;
    @Getter
    @Setter
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
    private boolean isPossibleToPaint = false;

    private TokenColor tokenColor;

    @Getter
    @Setter
    private TokenColor possibleTokenColor = NOT_SPECIFIED;

    @Setter
    private GameLogic gameLogic;




    @Getter
    @Setter
    private boolean isClicked = false;

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

    public TokenColor getTokenColor() {
        return tokenColor;
    }

    public void setTokenColor(TokenColor tokenColor) {
        this.tokenColor = tokenColor;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        setHighlighted(true);
        if (isHighlighted()) {
//            isHighlighted = false;
//            isPossibleToPaint = true;
            isClicked = true;
            tokenColor = getPossibleTokenColor();
            gameLogic.checkIsClicked();
        }
    }

//    public void paint(Graphics g){
//
//        if (isPossibleToPaint) {
//            Graphics2D g2d = (Graphics2D) g.create();
//            if (getTokenColor() == TokenColor.BLACK) {
//                g2d.setColor(Color.black);
//                g2d.fillOval(preferredSize.height / 4, preferredSize.width / 4, (int) ((double) getSize().width / 1.8), (int) ((double) getSize().width / 1.8));
//            }
//            if (getTokenColor() == TokenColor.WHITE) {
//                g2d.setColor(Color.white);
//                g2d.fillOval(preferredSize.height / 4, preferredSize.width / 4, (int) ((double) getSize().width / 1.8), (int) ((double) getSize().width / 1.8));
//            }
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
//        if (isHighlighted && isClicked && !tokenColor.equals(NOT_SPECIFIED)) {
////            var selectedColor =
//            g2d.setColor(tokenColor.equals(TokenColor.BLACK) ? Color.black : Color.white);
//            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
//        }
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

        if (isHighlighted) {
            setBorder(highlightedBorder);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(blackBorder);
        if (!isClicked) {
            tokenColor = NOT_SPECIFIED;
        }
        if (isHighlighted) setBorder(possibleBorder);

    }


    @Override
    public void highlightCell() {
//        if (this.getBorder().equals(possibleBorder)) setBorder(blackBorder);
        setBorder(possibleBorder);
    }

    public void removeHighlightCell() {
//        if (this.getBorder().equals(possibleBorder)) setBorder(blackBorder);
        setBorder(blackBorder);
    }
}
