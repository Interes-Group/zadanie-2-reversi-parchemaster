package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {

    private GameLogic gameLogic;


    public GameFrame() {

        createFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - getHeight()) / 4);
        setLocation(x, y);


        this.gameLogic = new GameLogic(8);
        add(gameLogic);

        centreWindow(this);
        //changes size of frame
        setResizable(true);
        pack();
        setVisible(true);
    }

    public static void centreWindow(Window frame) {
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
//        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
//        setLocation(x, y);
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
