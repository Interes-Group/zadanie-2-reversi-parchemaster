package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ChangeSize;
import sk.stuba.fei.uim.oop.button.Restart;
import sk.stuba.fei.uim.oop.gui.GameLogic;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InformationPanel extends JPanel {
    private JButton restart;
    private JComboBox changeSize;
    private JLabel currentPlayerInfo;
    private JLabel scoresLable;
    private Player player;
    private Player computer;
    private GameLogic gameLogic;
    private int size = 8;
    private GameBoardPanel gameBoardPanel;



    public InformationPanel(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame jFrame) {
        this.gameBoardPanel = gameBoardPanel;
//        this.player = gameLogic.getPlayer();
//        this.computer = gameLogic.getComputer();
        this.gameLogic = gameLogic;
        setLayout(new FlowLayout());
        restart = new JButton("Restart");
        changeSize = new JComboBox();
        currentPlayerInfo = new JLabel();
        scoresLable = new JLabel();
        setComboBox();

//        currentPlayerInfo.setText("Current player is " + player.getName().toLowerCase());
//        scoresLable.setText("Computer has: " + computer.getPlayerTokens().size()
//                + "    You have: " + player.getPlayerTokens().size());


        add(restart);
        add(new JSeparator());
        add(changeSize);
        add(new JSeparator());
        add(currentPlayerInfo);
        add(new JSeparator());
        add(scoresLable);

        var actionListener = new ChangeSize(gameLogic, gameBoardPanel, jFrame, size, changeSize, restart);
//        restart.addActionListener(new Restart(gameLogic, gameBoardPanel, jFrame, size, restart));
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
