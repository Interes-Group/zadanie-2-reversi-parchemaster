package sk.stuba.fei.uim.oop.button;

import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyButtonListener implements KeyListener {
//    private InformationPanel informationPanel;

    public KeyButtonListener() {
//        this.informationPanel = informationPanel;
        System.out.println("asd");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'r') {
            System.out.println("yes");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'r') {
            System.out.println("yes");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'r') {
            System.out.println("yes");
        }
    }
}
