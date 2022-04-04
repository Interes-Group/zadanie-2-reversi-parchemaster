package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{


//    private final JFrame gameFrame;





    public GameFrame() {

        createFrame();

        var gameLogic = new GameLogic(8);
        add(gameLogic);

        centreWindow(this);
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

    private JFrame createFrame() {
        var frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(500,500));
//        frame.setResizable(true);
//        frame.setMinimumSize(frame.getSize());
//        centreWindow(frame);
        return frame;
    }
}
