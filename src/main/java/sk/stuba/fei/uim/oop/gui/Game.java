package sk.stuba.fei.uim.oop.gui;

import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Information;

import javax.swing.*;
import java.awt.*;

public class Game {


    private final JFrame frame;

    @Setter
    private int size = 8;



    public Game() {

        frame = createFrame();



        // TODO implement select size, for now it's 8
        var gameBoardPanel = GameLogic.createGameBoardPanel(size);
        frame.add(gameBoardPanel, BorderLayout.CENTER);

        var infoPanel = new Information(size);
        frame.add(infoPanel, BorderLayout.SOUTH);

//        frame.add(game);



        frame.pack();
        frame.setVisible(true);
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
//        frame.setResizable(false);
//        frame.setMinimumSize(frame.getSize());
        centreWindow(frame);
        return frame;
    }
}
