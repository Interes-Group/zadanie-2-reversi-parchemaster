package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ActionChangeBord implements ActionListener {

    private GameLogic gameLogic;
    private JPanel gamePanel;
    private int newSizeBoard;

    public ActionChangeBord(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gamePanel = gameLogic.getGameBoardPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.remove(gamePanel);
//        SwingUtilities.updateComponentTreeUI(gameLogic.getGameBoardPanel());
        gamePanel = new GameBoardPanel(gameLogic.getBoardSize());
        gameLogic.add(gamePanel);
        SwingUtilities.updateComponentTreeUI(gameLogic);
    }
}
