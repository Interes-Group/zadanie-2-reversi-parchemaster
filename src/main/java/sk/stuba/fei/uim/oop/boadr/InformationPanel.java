package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ChangeSize;
import sk.stuba.fei.uim.oop.gui.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InformationPanel extends JPanel{
    private JButton restart;
    private JComboBox changeSize;
    private JLabel player1Info;
    private JLabel player2Info;



    public InformationPanel(GameLogic gameLogic) {
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


        // TODO doesn't work for restart
//        restart.addActionListener(new ChangeSize(gameLogic));
        changeSize.addActionListener(new ChangeSize(gameLogic));

        changeSize.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == changeSize) {

                    gameLogic.setBoardSize(Integer.parseInt(changeSize.getSelectedItem().toString().split("x")[0]));
//                    gameLogic.setBoardSize(newSizeBoard);
                }
            }
        });
    }



    private void setComboBox() {
        changeSize.addItem("8x8");
        changeSize.addItem("6x6");
        changeSize.addItem("10x10");
        changeSize.addItem("12x12");
    }
}
