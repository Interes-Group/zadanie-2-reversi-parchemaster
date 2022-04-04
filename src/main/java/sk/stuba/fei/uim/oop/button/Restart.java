package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.gui.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Restart extends AbstractAction {

    private JPanel panel;

    public Restart(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
    }
}
