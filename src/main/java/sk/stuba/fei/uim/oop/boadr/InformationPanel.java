package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.button.ActionButtons;

import sk.stuba.fei.uim.oop.button.Escape;
import sk.stuba.fei.uim.oop.gui.GameFrame;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class InformationPanel extends JPanel {
    @Getter
    private final JComboBox changeSize;
    @Getter
    private final JButton restart;
    @Getter
    private final ActionButtons actionListener;
    @Getter
    @Setter
    private JLabel infoSizeBoard = new JLabel();

    public InformationPanel(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame jFrame, JDialog finishDialog) {
        setLayout(new GridLayout(6, 1, 1, 1));
        restart = new JButton("Restart");
        changeSize = new JComboBox();
        JLabel currentPlayerInfo = new JLabel();
        JLabel playerLabel = new JLabel();
        var computerScore = new JLabel();
        setComboBox();

        var winnerLabel = new JLabel();
        winnerLabel = gameLogic.getWinnerLabel();

        var winnerPanel = new JPanel();
        winnerPanel.add(winnerLabel);
        finishDialog.setLayout(new BorderLayout());
        finishDialog.add(winnerPanel, BorderLayout.CENTER);
        finishDialog.setSize(300, 100);
        GameFrame.centreWindow(finishDialog);

        int size = 6;
        this.infoSizeBoard.setText("Size of the board is " + size + "x" + size);

        currentPlayerInfo = gameLogic.getCurrentPlayerLabel();
        playerLabel = gameLogic.getPlayerScore();
        computerScore = gameLogic.getComputerScore();

        add(currentPlayerInfo);
        add(playerLabel);
        add(computerScore);
        add(infoSizeBoard);
        add(restart);
        add(changeSize);

        var escListener = new Escape(jFrame);
        actionListener = new ActionButtons(gameLogic, gameBoardPanel, jFrame, size, this);
        restart.addActionListener(actionListener);
        changeSize.addActionListener(actionListener);
        ((JLabel)changeSize.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // restart button R
        restart.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,0), "R_pressed");
        restart.getActionMap().put("R_pressed", actionListener);

        // escape button esc
        jFrame.getRootPane().registerKeyboardAction(escListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void setComboBox() {
        changeSize.addItem("6x6");
        changeSize.addItem("8x8");
        changeSize.addItem("10x10");
        changeSize.addItem("12x12");
    }

}
