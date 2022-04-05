package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;

public class GameLogic extends JPanel{

//    private GameBoard board;

    @Getter
    @Setter
    private int boardSize;
    @Getter
    @Setter
    private GameBoardPanel gameBoardPanel;
    @Getter
    @Setter
    private InformationPanel informationPanel;
    @Getter
    @Setter
    private int round;
    private Player player1;
    private Player player2;


    private Cell[][] allCells;

    public GameLogic(int size) {


        this.boardSize = size;
        setLayout(new BorderLayout());

        gameBoardPanel = new GameBoardPanel(size);
        informationPanel = new InformationPanel(this);

        this.allCells = gameBoardPanel.getAllCells();

        add(gameBoardPanel, BorderLayout.NORTH);
        add(informationPanel, BorderLayout.SOUTH);

        //WHITE
        this.player1 = new Player(allCells, 2, TokenColor.WHITE);
        //BLACK
        this.player2 = new Player(allCells, 2, TokenColor.BLACK);

        start();
    }

    private void start() {
        var currentPlayer = round % 2 == 1 ? player1 : player2;


//        for (var cell : currentPlayer.getPlayerCells()) {
//            if ()
//        }
        var cells = currentPlayer.getPlayerCells();
//        for (int i = 0; i < cells.length; i++) {
//            for (int j = 0; j < cells.length; j++) {
//                if (cells[i][j].getTokenColor() != null) {
//                    if (cells[i][j].getTokenColor() == currentPlayer.getPlayerColor()) {
//                        findTheClosestOppositeToken(i, j, allCells[i][j].getTokenColor());
//                    }
//                }
//            }
//        }
        findCurrentPlayerToken(currentPlayer);
    }

    private void findCurrentPlayerToken(Player currentPlayer) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if (allCells[y][x].getTokenColor() == currentPlayer.getPlayerColor()) {
                    findTheClosestOppositeToken(y, x, allCells[y][x].getTokenColor());
                }
            }
        }
    }

    private TokenColor getOppositeTokenColor(TokenColor originColor) {
//        if (originColor.equals(TokenColor.NOT_SPECIFIED)) {
//            return TokenColor.NOT_SPECIFIED;
//        }
        return originColor.equals(TokenColor.BLACK) ? TokenColor.WHITE : TokenColor.BLACK;
    }

    private void findTheClosestOppositeToken(int origTokenY, int origTokenX, TokenColor origTokenColor) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if ((origTokenY == y - 1 || origTokenY == y + 1 || origTokenY == y)
                        && (origTokenX == x - 1 || origTokenX == x + 1 || origTokenX == x)
                        && !(allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED))) {
                    findTheClosestFreeToken(origTokenY, origTokenX, origTokenColor, y, x, allCells[y][x].getTokenColor());
                }
            }
        }
    }

    private void findTheClosestFreeToken(int origTokenY, int origTokenX, TokenColor origTokenColor, int possibleTokenI, int possibleTokenJ, TokenColor possibleTokenColor) {
        for (int Y = 0; Y < allCells.length; Y++) {
            for (int X = 0; X < allCells.length; X++) {
                //check top
                if (origTokenX == possibleTokenJ && possibleTokenJ == X
                        && origTokenY == possibleTokenI + 1 && possibleTokenI == Y + 1) {
                    allCells[Y][X].setHighlighted(true);
                    //TODO continue there
//                    allCells[i][j].setTokenColor(origTokenColor);
                }
                //check bottom
                if (origTokenX == possibleTokenJ && possibleTokenJ == X
                        && origTokenY == possibleTokenI - 1 && possibleTokenI == Y - 1) {
                    allCells[Y][X].setHighlighted(true);
//                    allCells[i][j].setTokenColor(origTokenColor);
                }
                // check right
                if (origTokenY == possibleTokenI && possibleTokenI == Y
                        && origTokenX == possibleTokenJ - 1 && possibleTokenJ == X - 1) {
                    allCells[Y][X].setHighlighted(true);
//                    allCells[i][j].setTokenColor(origTokenColor);
                }
                // check left
                if (origTokenY == possibleTokenI && possibleTokenI == Y
                        && origTokenX == possibleTokenJ + 1 && possibleTokenJ == X + 1) {
                    allCells[Y][X].setHighlighted(true);
//                    allCells[i][j].setTokenColor(origTokenColor);
                }
            }
        }
    }
}
