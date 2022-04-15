package sk.stuba.fei.uim.oop.button;

import lombok.Getter;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.gui.GameLogic;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ColorChoseAction extends AbstractAction {
    private final JButton blackButton;
    private final JButton whiteButton;
    private final Player player;
    private final Player computer;
    @Getter
    private final GameLogic gameLogic;
    private final JFrame frame;

    public ColorChoseAction(JButton blackButton, JButton whiteButton, Player player, Player computer, GameLogic gameLogic, JFrame frame) {
        this.gameLogic = gameLogic;
        this.frame = frame;
        this.blackButton = blackButton;
        this.whiteButton = whiteButton;
        this.player = player;
        this.computer = computer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == blackButton) {
            player.setPlayerColor(TokenColor.BLACK);
            computer.setPlayerColor(TokenColor.WHITE);
        }
        else if (e.getSource() == whiteButton){
            player.setPlayerColor(TokenColor.WHITE);
            computer.setPlayerColor(TokenColor.BLACK);
        }
        frame.setVisible(true);
        gameLogic.getColorDialog().setVisible(false);
    }
}
