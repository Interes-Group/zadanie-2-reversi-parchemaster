//package sk.stuba.fei.uim.oop.button;
//
//import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
//import sk.stuba.fei.uim.oop.gui.GameLogic;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//
//public class Restart implements ActionListener {
//
//    private JPanel gamePanel;
//    private GameLogic gameLogic;
//    private JFrame frame;
//    private int size;
//    private GameBoardPanel gameBoardPanel;
//    private JButton restart;
//
//    public Restart(GameLogic gameLogic, GameBoardPanel gameBoardPanel, JFrame frame, int size, JButton restart) {
//        this.frame = frame;
//        this.restart = restart;
//        this.gameBoardPanel = gameBoardPanel;
//        this.gameLogic = gameLogic;
//        this.size = size;
//    }
//
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        frame.remove(gameBoardPanel);
////        gameBoardPanel = new GameBoardPanel(size);
////        frame.add(gameBoardPanel);
////        SwingUtilities.updateComponentTreeUI(frame);
////        gameLogic = new GameLogic(gameBoardPanel);
////    }
//
////    @Override
////    public void itemStateChanged(ItemEvent e) {
////        if (e.getSource() == restart) {
////            frame.remove(gameBoardPanel);
////            gameBoardPanel = new GameBoardPanel(size);
////            frame.add(gameBoardPanel);
////            SwingUtilities.updateComponentTreeUI(frame);
////            gameLogic = new GameLogic(gameBoardPanel);
////        }
////    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        frame.remove(gameBoardPanel);
//        gameBoardPanel = new GameBoardPanel(size);
//        frame.add(gameBoardPanel);
//        SwingUtilities.updateComponentTreeUI(frame);
//        gameLogic = new GameLogic(gameBoardPanel);
//    }
//}
