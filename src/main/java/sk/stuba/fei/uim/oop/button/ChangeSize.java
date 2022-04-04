package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeSize implements ActionListener {

    private JFrame gameFrame;
    private JPanel gamePanel;
    private JComboBox newBoardSize;
    private int newSizeBoard;

    public ChangeSize(JFrame gameFrame, JPanel gamePanel, JComboBox changeSize) {
        this.gameFrame = gameFrame;
        this.gamePanel = gamePanel;
        this.newBoardSize = changeSize;

        changeSize.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == changeSize) {

                    newSizeBoard = Integer.parseInt(changeSize.getSelectedItem().toString().split("x")[0]);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameFrame.remove(gamePanel);
        gamePanel = new GameBoardPanel(newSizeBoard);
        gameFrame.add(gamePanel);
        SwingUtilities.updateComponentTreeUI(gameFrame);
    }
}
