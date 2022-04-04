package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.GameBoard;
import sk.stuba.fei.uim.oop.boadr.Information;

import javax.swing.*;
import java.awt.*;

public class GameLogic {

//    private GameBoard board;
    @Getter
    private int boardSize;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }



    public GameLogic(int size) {
        this.boardSize = size;



        // creating board
//        setLayout(new BorderLayout());
//        add(board, BorderLayout.CENTER);


        // creating information and buttons
//        var infoPanel = new Information(this, boardSize);
//        add(infoPanel, BorderLayout.SOUTH);
    }

    public static JPanel createGameBoardPanel(int size) {
        var board = new GameBoard(size);

        var activeToken1 = board.getAllCells()[(size-size/2) - 1][(size-size/2) - 1];
        activeToken1.setBackground(Color.red);
        var activeToken2 = board.getAllCells()[(size-size/2) - 1][(size-size/2)];
        activeToken2.setBackground(Color.blue);
        var activeToken3 = board.getAllCells()[(size-size/2)][(size-size/2) - 1];
        activeToken3.setBackground(Color.green);
        var activeToken4 = board.getAllCells()[(size-size/2)][(size-size/2)];
        activeToken4.setBackground(Color.yellow);

        return board;
    }

    private void putToken() {

    }
}
