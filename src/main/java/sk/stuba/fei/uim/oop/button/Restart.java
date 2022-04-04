package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Restart extends AbstractAction {

    private int oldBoardSize;
    final private JFrame gameFrame;
    private JPanel gamePanel;

    public Restart(int oldBoardSize, JFrame gameFrame, JPanel gamePanel) {
        this.oldBoardSize = oldBoardSize;
        this.gameFrame = gameFrame;
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameFrame.remove(gamePanel);
        gamePanel = new GameBoardPanel(oldBoardSize);
        gameFrame.add(gamePanel);
        SwingUtilities.updateComponentTreeUI(gameFrame);
    }
}
