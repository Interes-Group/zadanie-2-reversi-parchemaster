package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ChangeSize;
import sk.stuba.fei.uim.oop.button.Restart;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JPanel {
    private JButton restart;
    private JComboBox changeSize;
    private JLabel player1Info;
    private JLabel player2Info;



    public Information(int oldBoardSize) {

        setLayout(new FlowLayout());
        restart = new JButton("Restart");
        changeSize = new JComboBox();
        player1Info = new JLabel("you: score");
        player2Info = new JLabel("computer: score");
        setComboBox();

        add(restart);
        add(new JSeparator());
        add(changeSize);
        add(new JSeparator());
        add(player1Info);
        add(new JSeparator());
        add(player2Info);

//        restart.addActionListener(new Restart(mainPanel));
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                logicPanel.remove(oldBoardGamePanel);
//                logicPanel.repaint();
//                logicPanel.add(new GameBoard(oldBoardSize), BorderLayout.CENTER);
//                new GameLogic(mainPanel, 8);
            }
        });
//        changeSize.addActionListener(new ChangeSize(mainPanel, 10, oldBoardSize));

    }

    private void setComboBox() {
        changeSize.addItem("8x8");
        changeSize.addItem("6x6");
        changeSize.addItem("10x10");
        changeSize.addItem("12x12");
    }
}
