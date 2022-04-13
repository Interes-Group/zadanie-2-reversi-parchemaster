package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements ActionListener{

    private GameLogic gameLogic;
    private JButton resetButton;
    private JButton changeButton;
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

//        this.resetButton = new JButton("Reset");
//        this.changeButton = new JButton("Change size");
//        resetButton.addActionListener(this);
//        changeButton.addActionListener(this);

        var informationPanel = new InformationPanel(gameLogic, gameBoardPanel, this);


        this.add(gameBoardPanel, BorderLayout.NORTH);
        this.add(informationPanel, BorderLayout.SOUTH);
//        this.add(resetButton, BorderLayout.EAST);
//        this.add(changeButton, BorderLayout.WEST);

//        centreWindow(this);
        //changes size of frame
        setResizable(true);
        pack();
        setVisible(true);

        gameLogic = new GameLogic(gameBoardPanel);
    }

    public static void centreWindow(Window frame) {
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
//        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
//        setLocation(x, y);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(8);
            add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(this);
            gameLogic = new GameLogic(gameBoardPanel);
        }
        if (e.getSource() == changeButton) {
            remove(gameBoardPanel);
            gameBoardPanel = new GameBoardPanel(12);
            add(gameBoardPanel);
            SwingUtilities.updateComponentTreeUI(this);
            gameLogic = new GameLogic(gameBoardPanel);
        }
    }

//    private JFrame createFrame() {
//        var frame = new JFrame("Reversi");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.setPreferredSize(new Dimension(500,500));
////        frame.setResizable(true);
////        frame.setMinimumSize(frame.getSize());
////        centreWindow(frame);
//        return frame;
//    }
}
