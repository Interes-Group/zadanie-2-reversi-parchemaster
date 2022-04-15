package sk.stuba.fei.uim.oop.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Escape implements ActionListener {
    private final JFrame frame;

    public Escape(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        System.exit(0);
    }
}
