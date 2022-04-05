package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSize implements ActionListener {

    private JPanel gamePanel;
    private GameLogic gameLogic;

    public ChangeSize(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gamePanel = gameLogic.getGameBoardPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.remove(gamePanel);
        gamePanel = new GameBoardPanel(gameLogic.getBoardSize());
        gameLogic.add(gamePanel);
        gameLogic.revalidate();
        gameLogic.repaint();
    }
}
