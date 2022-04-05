//package sk.stuba.fei.uim.oop.button;
//
//import sk.stuba.fei.uim.oop.boadr.GameBordPanel;
//import sk.stuba.fei.uim.oop.gui.GameLogic;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//
//public class Restart extends AbstractAction {
//
//    private GameLogic gameLogic;
//    private JPanel gamePanel;
//
//    public Restart(GameLogic gameLogic) {
//        this.gameLogic = gameLogic;
//        this.gamePanel = gameLogic.getGameBordPanel();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        gameLogic.remove(gamePanel);
//        gamePanel = new GameBordPanel(8);
//        gameLogic.add(gamePanel);
//        SwingUtilities.updateComponentTreeUI(gameLogic);
//    }
//}
