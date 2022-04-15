package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.button.ColorChoseAction;
import sk.stuba.fei.uim.oop.button.KeyButtonListener;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;

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
    //TODO try = new KeyListener()
    @Getter
    private KeyButtonListener keyButtonListener;

    @Getter
    private Player player;
    @Getter
    private Player computer;



    public GameFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - getHeight()) / 4);
        setLocation(x, y);


//        setDialogColor();
        this.setSize(600, 500);
        this.setLayout(new BorderLayout());
        this.gameBoardPanel = new GameBoardPanel(8);
        gameLogic = new GameLogic(gameBoardPanel, playerScore, computerScore, currentPlayerLabel, finishDialog, winnerName, this);
        var informationPanel = new InformationPanel(gameLogic, gameBoardPanel, this, finishDialog);

//        gameLogic.createColorButtons();
//
        gameLogic.createColorButtons();
//        keyButtonListener = new KeyButtonListener();
//        addKeyListener(keyButtonListener);

        this.add(gameBoardPanel, BorderLayout.NORTH);
        this.add(informationPanel, BorderLayout.SOUTH);

        setResizable(true);
        pack();
//        setVisible(true);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

//    private void setDialogColor() {
//        colorDialog.setLayout(new BorderLayout());
//        var blackButton = new JButton("Black");
//        var whiteButton = new JButton("White");
//        var colorChoseAction = new ColorChoseAction(blackButton, whiteButton, player, computer, gameLogic, this);
//        blackButton.addActionListener(colorChoseAction);
//        whiteButton.addActionListener(colorChoseAction);
//        colorDialog.add(new JLabel("What color do you want to play?"), BorderLayout.NORTH);
//        colorDialog.add(whiteButton, BorderLayout.WEST);
//        colorDialog.add(blackButton, BorderLayout.EAST);
//        colorDialog.setSize(300, 100);
//        GameFrame.centreWindow(colorDialog);
//        colorDialog.setModal(true);
//        colorDialog.setVisible(true);
//    }
}
