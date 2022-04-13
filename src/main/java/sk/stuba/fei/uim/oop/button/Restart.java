package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Restart extends AbstractAction {

    private JPanel gamePanel;
    private GameLogic gameLogic;
    private JFrame frame;
    private int size;
    private GameBoardPanel gameBoardPanel;

    public Restart(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame frame, int size) {
        this.frame = frame;
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
        this.size = size;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.remove(gameBoardPanel);
        gameBoardPanel = new GameBoardPanel(size);
        frame.add(gameBoardPanel);
        SwingUtilities.updateComponentTreeUI(frame);
        gameLogic = new GameLogic(gameBoardPanel);
    }
}
