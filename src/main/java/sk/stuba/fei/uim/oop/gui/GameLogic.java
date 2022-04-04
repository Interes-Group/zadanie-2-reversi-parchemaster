package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;

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

    }

}
