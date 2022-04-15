package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

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

    public GameFrame() {
        createFrame();

        GameBoardPanel gameBoardPanel = new GameBoardPanel(6);
        GameLogic gameLogic = new GameLogic(gameBoardPanel, playerScore, computerScore, currentPlayerLabel, finishDialog, winnerName, this);

        var informationPanel = new InformationPanel(gameLogic, gameBoardPanel, this, finishDialog);
        gameLogic.createColorButtons();

        this.add(gameBoardPanel, BorderLayout.NORTH);
        this.add(informationPanel, BorderLayout.SOUTH);

        setResizable(false);
        pack();
    }

    private void createFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        centreWindow(this);
        this.setLayout(new BorderLayout());
    }

    public static void centreWindow(Window window) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
        window.setLocation(x, y - 100);
    }
}
