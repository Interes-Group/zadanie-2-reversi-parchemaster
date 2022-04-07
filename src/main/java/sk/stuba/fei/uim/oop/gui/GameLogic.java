package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.Move;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLogic extends JPanel {

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

    @Getter
    @Setter
    private ArrayList<Cell> possibleCells = new ArrayList<>();

    @Getter
    @Setter
    private boolean isNewRound;

    @Getter
    @Setter
    private ArrayList<HashMap<Cell, ArrayList<Cell>>> possibleWaysToMove;



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
        this.player1 = new Player(new ArrayList<Cell>(), 2, TokenColor.WHITE);
        //BLACK
        this.player2 = new Player(new ArrayList<Cell>(), 2, TokenColor.BLACK);


        start();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);


    }

    Runnable helloRunnable = new Runnable() {
        public void run() {
            checkIsClicked();
        }
    };


    private void checkIsClicked() {
        if (possibleCells.stream().anyMatch(cell -> cell.isClicked())) {
            var newToken = possibleCells.stream().filter(cell -> cell.isClicked()).findFirst().get();
            round ++;
            removeHighlightedCells();
            possibleCells.clear();
//            possibleCells.removeAll(possibleCells);
            start();
        }
    }


    public void start() {
        var currentPlayer = round % 2 == 1 ? player1 : player2;
        var opponentPlayer = round % 2 == 0 ? player1 : player2;
//        findCurrentPlayerToken(currentPlayer);
        findPlayersTokens(currentPlayer, opponentPlayer);
        findTheClosestCellsNearToCurrentPlayer(currentPlayer, opponentPlayer);
        setHighlightedCells(currentPlayer);

        isNewRound = false;
    }



    private void findPlayersTokens(Player currentPlayer, Player opponentPlayer) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if (allCells[y][x].getTokenColor() == currentPlayer.getPlayerColor()
                        && !currentPlayer.getPlayerTokens().contains(allCells[y][x])) {
                    currentPlayer.getPlayerTokens().add(allCells[y][x]);
                }
                if (allCells[y][x].getTokenColor() == opponentPlayer.getPlayerColor()
                        && !opponentPlayer.getPlayerTokens().contains(allCells[y][x])) {
                    opponentPlayer.getPlayerTokens().add(allCells[y][x]);
                }
            }
        }
    }

    private void findTheClosestCellsNearToCurrentPlayer(Player currentPlayer, Player opponentPlayer) {
        for (var currentPlayerToken : currentPlayer.getPlayerTokens()) {
            //make own functio
//            var listOfMoves = new ArrayList<Move>();
//            for (var opponentPlayerToken : opponentPlayer.getPlayerTokens()) {
//                // north
//                if (currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX()
//                        && currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1) {
//                    listOfMoves.add(Move.NORTH);
//                }
//                // north east
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
//                    listOfMoves.add(Move.NORTH_EAST);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY()
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
//                    listOfMoves.add(Move.EAST);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
//                    listOfMoves.add(Move.SOUTH_EAST);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX()) {
//                    listOfMoves.add(Move.SOUTH);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
//                    listOfMoves.add(Move.SOUTH_WEST);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY()
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
//                    listOfMoves.add(Move.WEST);
//                }
//                if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1
//                        && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
//                    listOfMoves.add(Move.NORTH_WEST);
//                }
//            }
            // end of own function
            var listOfMoves = findListOfMoves(opponentPlayer, currentPlayerToken);
            findTheEndOfWayForSpecificToken(listOfMoves, currentPlayerToken);
//            wayFromCurrentToken.put(currentPlayerToken, listOfMoves);
//
//            possibleWaysForCurrentToken.put(currentPlayerToken, listOfPossibleOpponentTokens);
//            possibleWaysToMove.add(possibleWaysForCurrentToken);
        }
    }

    private ArrayList<Move> findListOfMoves(Player opponent, Cell currentPlayerToken) {
        var listOfMoves = new ArrayList<Move>();
        for (var opponentPlayerToken : opponent.getPlayerTokens()) {
            // north
            if (currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX()
                    && currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1) {
                listOfMoves.add(Move.NORTH);
            }
            // north east
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
                listOfMoves.add(Move.NORTH_EAST);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY()
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
                listOfMoves.add(Move.EAST);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1) {
                listOfMoves.add(Move.SOUTH_EAST);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX()) {
                listOfMoves.add(Move.SOUTH);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
                listOfMoves.add(Move.SOUTH_WEST);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY()
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
                listOfMoves.add(Move.WEST);
            }
            if (currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1
                    && currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1) {
                listOfMoves.add(Move.NORTH_WEST);
            }
        }
        return listOfMoves;
    }


    private void findTheEndOfWayForSpecificToken(ArrayList<Move> moves, Cell currentToken) {
            for (var move : moves) {
                if (move.equals(Move.NORTH)) {
                    for (int y = currentToken.getPositionY() - 2; y >= 0; y--) {
                        if (allCells[y][currentToken.getPositionX()].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                            if (!possibleCells.contains(allCells[y][currentToken.getPositionX()])) possibleCells.add(allCells[y][currentToken.getPositionX()]);
                            break;
                        }
                    }
                } else if (move.equals(Move.NORTH_EAST)) {
                    for (int y = currentToken.getPositionY() - 2; y >= 0; y--) {
                        for (int x = currentToken.getPositionX() + 2; x < boardSize; x++) {
                            if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                                if (!possibleCells.contains(allCells[y][x])) possibleCells.add(allCells[y][x]);
                                break;
                            }
                        }
                    }
                } else if (move.equals(Move.EAST)) {
                    for (int x = currentToken.getPositionX() + 2; x < boardSize; x++) {
                        if (allCells[currentToken.getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                            if (!possibleCells.contains(allCells[currentToken.getPositionY()][x])) possibleCells.add(allCells[currentToken.getPositionY()][x]);
                            break;
                        }
                    }
                } else if (move.equals(Move.SOUTH_EAST)) {
                    for (int y = currentToken.getPositionY() + 2; y < boardSize; y++) {
                        for (int x = currentToken.getPositionX() + 1; x < boardSize; x++) {
                            if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                                if (!possibleCells.contains(allCells[y][x])) possibleCells.add(allCells[y][x]);
                                break;
                            }
                        }
                    }
                } else if (move.equals(Move.SOUTH)) {
                    for (int y = currentToken.getPositionY() + 2; y < boardSize; y++) {
                        if (allCells[y][currentToken.getPositionX()].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                            if (!possibleCells.contains(allCells[y][currentToken.getPositionX()])) possibleCells.add(allCells[y][currentToken.getPositionX()]);
                            break;
                        }
                    }
                } else if (move.equals(Move.SOUTH_WEST)) {
                    for (int y = currentToken.getPositionY() + 2; y < boardSize; y++) {
                        for (int x = currentToken.getPositionX() - 2; x <= 0; x--) {
                            if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                                if (!possibleCells.contains(allCells[y][x])) possibleCells.add(allCells[y][x]);
                                break;
                            }
                        }
                    }
                } else if (move.equals(Move.WEST)) {
                    for (int x = currentToken.getPositionX() - 2; x >= 0; x--) {
                        if (allCells[currentToken.getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                            if (!possibleCells.contains(allCells[currentToken.getPositionY()][x])) possibleCells.add(allCells[currentToken.getPositionY()][x]);
                            break;
                        }
                    }
                } else if (move.equals(Move.NORTH_WEST)) {
                    for (int y = currentToken.getPositionY() - 2; y >= 0; y--) {
                        for (int x = currentToken.getPositionX() - 2; x <= 0; x--) {
                            if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                                if (!possibleCells.contains(allCells[y][x])) possibleCells.add(allCells[y][x]);
                                break;
                            }
                        }
                    }
                }
            }
    }

    private void setHighlightedCells(Player currentPlayer) {
        for (var cell : possibleCells) {
            cell.setHighlighted(true);
            cell.setPossibleTokenColor(currentPlayer.getPlayerColor());
        }
    }

    private void removeHighlightedCells() {
        for (var cell : possibleCells) {
            cell.setHighlighted(false);
            cell.setPossibleTokenColor(TokenColor.NOT_SPECIFIED);
        }
    }

    private void flipOpponentsTokens() {

    }

    // Second way to check the end of way
//    private void checkTheEndOfMove(HashMap<Cell, ArrayList<Move>> wayFromCurrentToken, Player currentPlayer, Player opponentPlayer) {
//        for (var token : wayFromCurrentToken.keySet()) {
//            for (var moves : wayFromCurrentToken.values()) {
//                for (var move : moves) {
//                    if (findNextFreeCell(move, currentPlayer.getPlayerColor(), token)) {
//                        //TODO there
//                    }
//                }
//            }
//        }
//    }

//    private boolean findNextFreeCell(Move move, TokenColor playerColor, Cell currentCell) {
//        if (move.equals(Move.NORTH)) {
//
//        }
//        for (int x = 0; x < boardSize; x++) {
//            for (int y = 0; y < boardSize; y++) {
//
//            }
//        }
//    }



    private void findCurrentPlayerToken(Player currentPlayer) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if (allCells[y][x].getTokenColor() == currentPlayer.getPlayerColor()) {
                    findTheClosestOppositeToken(y, x, allCells[y][x].getTokenColor());
                }
                if (isNewRound) return;
            }
        }
    }

    private TokenColor getOppositeTokenColor(TokenColor originColor) {
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
                if (isNewRound){
                    return;
                }
            }
        }
    }

    private void findTheClosestFreeToken(int origTokenY, int origTokenX, TokenColor origTokenColor, int possibleTokenY, int possibleTokenX, TokenColor possibleTokenColor) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if (!possibleCells.contains(allCells[y][x])) {
                    //check top
                    if (origTokenX == possibleTokenX && possibleTokenX == x
                            && origTokenY == possibleTokenY + 1 && possibleTokenY == y + 1) {
                        allCells[y][x].setHighlighted(true);
                        allCells[y][x].setPossibleTokenColor(origTokenColor);
                        possibleCells.add(allCells[y][x]);
                        break;
                    }
                    //check bottom
                    if (origTokenX == possibleTokenX && possibleTokenX == x
                            && origTokenY == possibleTokenY - 1 && possibleTokenY == y - 1) {
                        allCells[y][x].setHighlighted(true);
                        allCells[y][x].setPossibleTokenColor(origTokenColor);
                        possibleCells.add(allCells[y][x]);
                        break;
                    }
                    // check right
                    if (origTokenY == possibleTokenY && possibleTokenY == y
                            && origTokenX == possibleTokenX - 1 && possibleTokenX == x - 1) {
                        allCells[y][x].setHighlighted(true);
                        allCells[y][x].setPossibleTokenColor(origTokenColor);
                        possibleCells.add(allCells[y][x]);
                        break;
                    }
                    // check left
                    if (origTokenY == possibleTokenY && possibleTokenY == y
                            && origTokenX == possibleTokenX + 1 && possibleTokenX == x + 1) {
                        allCells[y][x].setHighlighted(true);
                        allCells[y][x].setPossibleTokenColor(origTokenColor);
                        possibleCells.add(allCells[y][x]);
                        break;
                    }
                    if (possibleCells.stream().anyMatch(cell -> cell.isClicked())) {
                        round++;
                        isNewRound = true;
                        possibleCells.stream().forEach(cell -> cell.setHighlighted(false));
                        possibleCells.removeAll(possibleCells);
                        return;
                    }
                }
            }
        }
    }


//    private void removeOldPossibleTokens() {
//        possibleCells.removeAll(possibleCells);
//    }

}
