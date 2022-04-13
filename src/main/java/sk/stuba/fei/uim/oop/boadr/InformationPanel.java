package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.ChangeSize;
import sk.stuba.fei.uim.oop.gui.GameLogic;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InformationPanel extends JPanel{
    private JButton restart;
    private JComboBox changeSize;
    private JLabel currentPlayerInfo;
    private JLabel scoresLable;
    private Player player;
    private Player computer;



    public InformationPanel(GameLogic gameLogic) {
        this.player = gameLogic.getPlayer();
        this.computer = gameLogic.getComputer();
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
