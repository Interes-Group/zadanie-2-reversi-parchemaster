package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ActionButtons;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;


public class InformationPanel extends JPanel {
    private JComboBox changeSize;
    private JLabel playerLabel;
    private GameLogic gameLogic;
    private int size = 8;
    private GameBoardPanel gameBoardPanel;



    public InformationPanel(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame jFrame) {
        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;
//        setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        JButton restart = new JButton("Restart");
        changeSize = new JComboBox();
        JLabel currentPlayerInfo = new JLabel();
        playerLabel = new JLabel();
        var computerScore = new JLabel();
        setComboBox();

        currentPlayerInfo = gameLogic.getCurrentPlayerLabel();
        playerLabel = gameLogic.getPlayerScore();
        computerScore = gameLogic.getComputerScore();

        add(restart, BorderLayout.SOUTH);
//        add(new JSeparator());
        add(changeSize, BorderLayout.WEST);
//        add(new JSeparator());
        add(currentPlayerInfo, BorderLayout.NORTH);
//        add(new JSeparator());
        add(playerLabel, BorderLayout.CENTER);
//        add(new JSeparator());
//        add(new JSeparator());
        add(computerScore, BorderLayout.EAST);

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
