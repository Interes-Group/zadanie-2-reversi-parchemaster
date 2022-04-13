package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {

    private GameLogic gameLogic;
    private GameBoardPanel gameBoardPanel;


    public GameFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - getHeight()) / 4);
        setLocation(x, y);

        this.setSize(600, 500);
        this.setLayout(new BorderLayout());
        this.gameBoardPanel = new GameBoardPanel(8);
        gameLogic = new GameLogic(gameBoardPanel);

        var informationPanel = new InformationPanel(gameLogic, gameBoardPanel, this);


        this.add(gameBoardPanel, BorderLayout.NORTH);
        this.add(informationPanel, BorderLayout.SOUTH);


//        centreWindow(this);
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
}
