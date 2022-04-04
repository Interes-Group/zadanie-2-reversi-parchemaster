package sk.stuba.fei.uim.oop.boadr;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    @Getter
    private Cell[][] allCells;

    public GameBoard(int size) {
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
    }

//    private Cell getSpecificCell(int y, int x) {
//        return getSpecificCell()
//    }
}
