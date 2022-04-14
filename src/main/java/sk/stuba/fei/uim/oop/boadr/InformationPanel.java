package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ActionButtons;
import sk.stuba.fei.uim.oop.gui.GameFrame;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;


public class InformationPanel extends JPanel {
    private JComboBox changeSize;
    private JLabel playerLabel;
    private GameLogic gameLogic;
    private int size = 8;
    private GameBoardPanel gameBoardPanel;



    public InformationPanel(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame jFrame, JDialog finishDialog) {
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
        setLayout(new GridLayout(5, 1, 1, 1));
        JButton restart = new JButton("Restart");
        changeSize = new JComboBox();
        JLabel currentPlayerInfo = new JLabel();
        playerLabel = new JLabel();
        var computerScore = new JLabel();
        setComboBox();

        var winner = gameLogic.getPlayer().getPlayerTokens().size() > gameLogic.getComputer().getPlayerTokens().size()
                ? gameLogic.getPlayer().getName() : gameLogic.getComputer().getName();
        var winnerPanel = new JPanel();
        finishDialog.setLayout(new BorderLayout());
        winnerPanel.add(new JLabel(winner + " won tha game"));
        finishDialog.add(winnerPanel, BorderLayout.CENTER);
        finishDialog.setSize(300, 100);
        GameFrame.centreWindow(finishDialog);

        currentPlayerInfo = gameLogic.getCurrentPlayerLabel();
        playerLabel = gameLogic.getPlayerScore();
        computerScore = gameLogic.getComputerScore();

        add(currentPlayerInfo);
        add(playerLabel);
        add(computerScore);
        add(restart);
        add(changeSize);

        var actionListener = new ActionButtons(gameLogic, gameBoardPanel, jFrame, size, changeSize, restart);
        restart.addActionListener(actionListener);
        changeSize.addActionListener(actionListener);
    }

    private void setComboBox() {
        changeSize.addItem("8x8");
        changeSize.addItem("6x6");
        changeSize.addItem("10x10");
        changeSize.addItem("12x12");
    }

}
