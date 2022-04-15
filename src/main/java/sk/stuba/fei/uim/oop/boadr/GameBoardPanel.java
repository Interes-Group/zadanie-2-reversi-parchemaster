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
                var preferredSize =  new Dimension(500 / size, 500 / size);
                allCells[y][x] = new Cell(y, x, preferredSize);
                allCells[y][x].setPreferredSize(preferredSize);
                add(allCells[y][x]);
            }
        }
        var activeToken1 = getAllCells()[(size-size/2) - 1][(size-size/2) - 1];
        activeToken1.setTokenColor(TokenColor.BLACK);

        var activeToken2 = getAllCells()[(size-size/2) - 1][(size-size/2)];
        activeToken2.setTokenColor(TokenColor.WHITE);

        var activeToken3 = getAllCells()[(size-size/2)][(size-size/2) - 1];
        activeToken3.setTokenColor(TokenColor.WHITE);

        var activeToken4 = getAllCells()[(size-size/2)][(size-size/2)];
        activeToken4.setTokenColor(TokenColor.BLACK);
    }
}
