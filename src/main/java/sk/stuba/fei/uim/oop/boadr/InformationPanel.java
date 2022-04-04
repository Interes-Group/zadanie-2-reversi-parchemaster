package sk.stuba.fei.uim.oop.boadr;

import sk.stuba.fei.uim.oop.button.Restart;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JButton restart;
    private JComboBox changeSize;
    private JLabel player1Info;
    private JLabel player2Info;



    public InformationPanel(int oldBoardSize, JFrame gameFrame, JPanel gamePanel) {
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

        restart.addActionListener(new Restart(oldBoardSize, gameFrame, gamePanel));
//

    }

    private void setComboBox() {
        changeSize.addItem("8x8");
        changeSize.addItem("6x6");
        changeSize.addItem("10x10");
        changeSize.addItem("12x12");
    }
}
