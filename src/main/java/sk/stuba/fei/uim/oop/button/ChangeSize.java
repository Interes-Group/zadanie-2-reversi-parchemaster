package sk.stuba.fei.uim.oop.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSize implements ActionListener {

    private JPanel panel;
    private int size;
    private int oldBoardSize;

    public ChangeSize(JPanel panel, int newBoardSize, int oldBoardSize) {
        this.panel = panel;
        this.size = newBoardSize;
        this.oldBoardSize = oldBoardSize;
        oldBoardSize = newBoardSize;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
    }
}
