package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.button.KeyButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private GameLogic gameLogic;
    private GameBoardPanel gameBoardPanel;

    @Getter
    private JLabel playerScore = new JLabel();
    @Getter
    private JLabel computerScore = new JLabel();
    @Getter
    private JLabel currentPlayerLabel = new JLabel();
    @Getter
    private JDialog finishDialog = new JDialog(this, "Game is finished", true);
    @Getter
    private JLabel winnerName = new JLabel();
    @Getter
    private KeyButtonListener keyButtonListener;



    public GameFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - getHeight()) / 4);
        setLocation(x, y);

        this.setSize(600, 500);
        this.setLayout(new BorderLayout());
        this.gameBoardPanel = new GameBoardPanel(8);
        gameLogic = new GameLogic(gameBoardPanel, playerScore, computerScore, currentPlayerLabel, finishDialog, winnerName);
        var informationPanel = new InformationPanel(gameLogic, gameBoardPanel, this, finishDialog);


//        keyButtonListener = new KeyButtonListener();
//        addKeyListener(keyButtonListener);

        this.add(gameBoardPanel, BorderLayout.NORTH);
        this.add(informationPanel, BorderLayout.SOUTH);

        setResizable(true);
        pack();
        setVisible(true);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
