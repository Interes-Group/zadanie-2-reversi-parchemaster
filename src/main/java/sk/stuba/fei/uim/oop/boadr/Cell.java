package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import static sk.stuba.fei.uim.oop.boadr.TokenColor.NOT_SPECIFIED;

public class Cell extends JPanel implements MouseListener, Painter {

    private final Dimension preferredSize;

    private final Border highlightedBorder = BorderFactory.createLineBorder(Color.cyan,2);
    private final Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
    private final Border possibleBorder = BorderFactory.createLineBorder(Color.WHITE);
    @Getter
    @Setter
    private int positionX;
    @Getter
    @Setter
    private int positionY;
    @Getter
    private final Color highlightedColor = Color.red;
    @Getter
    @Setter
    private boolean isHighlighted;
    @Getter
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
        this.preferredSize = preferredSize;
        this.positionX = x;
        this.positionY = y;
        setBackground(Color.gray);
        setBorder(blackBorder);
        addMouseListener(this);
        setFocusable(true);
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
        if (isHighlighted()) {
            isClicked = true;
            tokenColor = getPossibleTokenColor();
            gameLogic.checkIsClicked();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (getTokenColor() == TokenColor.BLACK) {
            g2d.setColor(Color.black);
            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
        }
        if (getTokenColor() == TokenColor.WHITE) {
            g2d.setColor(Color.white);
            g2d.fillOval(preferredSize.height/4 ,preferredSize.width/4, (int)((double)getSize().width/1.8), (int)((double)getSize().width/1.8));
        }
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
        setBorder(possibleBorder);
    }

    @Override
    public void removeHighlightCell() {
        setBorder(blackBorder);
    }
}
