package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeSize implements ItemListener {

    private JPanel gamePanel;
    private GameLogic gameLogic;
    private JFrame frame;
    private int size;
    private GameBoardPanel gameBoardPanel;
    private JComboBox changeSize;

    public ChangeSize(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame frame, int size, JComboBox changeSize) {
        this.frame = frame;
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
        this.changeSize = changeSize;
        this.size = size;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == changeSize) {
            size = Integer.parseInt(changeSize.getSelectedItem().toString().split("x")[0]);
            frame.remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(size);
            frame.add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            gameLogic = new GameLogic(gameBoardPanel);
        }
    }
}
