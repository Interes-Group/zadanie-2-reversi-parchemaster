package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionButtons implements ActionListener, KeyListener {

    private JPanel gamePanel;
    private GameLogic gameLogic;
    private JFrame frame;
    private int size;
    private GameBoardPanel gameBoardPanel;
    private JComboBox changeSize;
    private JButton restart;

    public ActionButtons(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame frame, int size, JComboBox changeSize, JButton restart) {
        this.frame = frame;
        this.restart = restart;
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
        this.changeSize = changeSize;
        this.size = size;
        frame.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeSize) {
            size = Integer.parseInt(changeSize.getSelectedItem().toString().split("x")[0]);
            frame.remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(size);
            frame.add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            gameLogic = new GameLogic(gameBoardPanel, gameLogic.getPlayerScore(), gameLogic.getComputerScore(), gameLogic.getCurrentPlayerLabel(), gameLogic.getFinishDialog(), gameLogic.getWinnerLabel(), frame);
            gameLogic.createColorButtons();
        }
        if (e.getSource() == restart) {
            size = 8;
            frame.remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(size);
            frame.add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            gameLogic = new GameLogic(gameBoardPanel, gameLogic.getPlayerScore(), gameLogic.getComputerScore(), gameLogic.getCurrentPlayerLabel(), gameLogic.getFinishDialog(), gameLogic.getWinnerLabel(), frame);
            gameLogic.createColorButtons();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r') {
            size = 8;
            frame.remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(size);
            frame.add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            gameLogic = new GameLogic(gameBoardPanel, gameLogic.getPlayerScore(), gameLogic.getComputerScore(), gameLogic.getCurrentPlayerLabel(), gameLogic.getFinishDialog(), gameLogic.getWinnerLabel(), frame);
            gameLogic.createColorButtons();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
