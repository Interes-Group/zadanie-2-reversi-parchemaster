package sk.stuba.fei.uim.oop.gui;

import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame {


    private final JFrame gameFrame;


    @Setter
    private int size = 8;



    public GameFrame() {

        gameFrame = createFrame();



        // TODO implement select size, for now it's 8
//        var gameBoardPanel = GameLogic.createGameBoardPanel(size);
        var gameBoardPanel = new GameBoardPanel(size);
        var infoPanel = new InformationPanel(size, gameFrame, gameBoardPanel);

        gameFrame.add(gameBoardPanel, BorderLayout.CENTER);
        gameFrame.add(infoPanel, BorderLayout.SOUTH);

//        frame.add(game);



        gameFrame.pack();
        gameFrame.setVisible(true);
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
