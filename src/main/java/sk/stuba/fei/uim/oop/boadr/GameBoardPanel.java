package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {

    @Getter
    private Cell[][] allCells;

    public GameBoardPanel(int size) {
        setLayout(new GridLayout(size, size));


        // builds naked cells without tokens
        allCells = new Cell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                var preferredSize =  new Dimension(500 / 8, 500 / 8);
                allCells[y][x] = new Cell(y, x, preferredSize);
                allCells[y][x].setPreferredSize(preferredSize);
                add(allCells[y][x]);
            }
        }
        var activeToken1 = getAllCells()[(size-size/2) - 1][(size-size/2) - 1];
        activeToken1.setBackground(Color.red);
        var activeToken2 = getAllCells()[(size-size/2) - 1][(size-size/2)];
        activeToken2.setBackground(Color.blue);
        var activeToken3 = getAllCells()[(size-size/2)][(size-size/2) - 1];
        activeToken3.setBackground(Color.green);
        var activeToken4 = getAllCells()[(size-size/2)][(size-size/2)];
        activeToken4.setBackground(Color.yellow);
    }

//    private Cell getSpecificCell(int y, int x) {
//        return getSpecificCell()
//    }
}
