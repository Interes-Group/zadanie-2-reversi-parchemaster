package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ActionButtons extends AbstractAction {

    private GameLogic gameLogic;
    private final JFrame frame;
    private int size;
    private GameBoardPanel gameBoardPanel;
//    private final JComboBox changeSize;
//    private final JButton restart;
    private InformationPanel informationPanel;

    public ActionButtons(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame frame, int size, InformationPanel informationPanel) {
        this.frame = frame;
//        this.restart = restart;
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
//        this.changeSize = changeSize;
        this.informationPanel = informationPanel;
        this.size = size;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == informationPanel.getChangeSize()) {
            size = Integer.parseInt(Objects.requireNonNull(informationPanel.getChangeSize().getSelectedItem()).toString().split("x")[0]);
            informationPanel.getInfoSizeBoard().setText("Size of the board is " + size + "x" + size);
        }
        frame.remove(gameBoardPanel);
        gameBoardPanel = new GameBoardPanel(size);
        frame.add(gameBoardPanel);
        SwingUtilities.updateComponentTreeUI(frame);
        gameLogic = new GameLogic(gameBoardPanel,
                gameLogic.getPlayerScore(),
                gameLogic.getComputerScore(),
                gameLogic.getCurrentPlayerLabel(),
                gameLogic.getFinishDialog(),
                gameLogic.getWinnerLabel(),
                frame);
        gameLogic.createColorButtons();
    }
}
