package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.Tuple;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.KeyPair;
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
    private ArrayList<Dictionary<Cell, ArrayList<Cell>>> possibleWaysToMove;



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


//        start();

//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);





    }

//    Runnable helloRunnable = new Runnable() {
//        public void run() {
//
//            start();
//        }
//    };



    public void start() {
        var currentPlayer = round % 2 == 1 ? player1 : player2;
        var opponentPlayer = round % 2 == 0 ? player1 : player2;
        findCurrentPlayerToken(currentPlayer);
        isNewRound = false;
    }



    private void findPlayersTokens(Player currentPlayer, Player opponentPlayer) {
        for (int y = 0; y < allCells.length; y++) {
            for (int x = 0; x < allCells.length; x++) {
                if (allCells[y][x].getTokenColor() == currentPlayer.getPlayerColor()) {
                    currentPlayer.getPlayerTokens().add(allCells[y][x]);
                }
                if (allCells[y][x].getTokenColor() != currentPlayer.getPlayerColor()
                        && allCells[y][x].getTokenColor() != TokenColor.NOT_SPECIFIED) {
                    opponentPlayer.getPlayerTokens().add(allCells[y][x]);
                }
            }
        }
    }

    private void findTheClosestCellsNearToCurrentPlayer(Player currentPlayer, Player opponentPlayer) {
        for (var currentPlayerToken : currentPlayer.getPlayerTokens()) {
            var possibleWaysForCurrentToken = new HashMap<Cell, ArrayList<Cell>>();
            var listOfPossibleOpponentTokens = new ArrayList<Cell>();

            for (var opponentPlayerToken : opponentPlayer.getPlayerTokens()) {
                if ((currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() - 1
                        || currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY() + 1
                        || currentPlayerToken.getPositionY() == opponentPlayerToken.getPositionY())
                        && (currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() - 1
                        || currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX() + 1
                        || currentPlayerToken.getPositionX() == opponentPlayerToken.getPositionX())) {
                    // TODO looking for matches between White and Black tokens (Continue there (не учел диагонали))
                    // TODO 1) сделать список вариантов аттаки для каждого токена текущего игрока (например токен 4,4 может атаковать 3,4 и 4,3)
                    // TODO 2) пройти в цикле через каждую возможную атаку у узнать можно ли в следствии атаки захватить токен(токены) противоположного цвета
                    // TODO 3) когда мы поставили токен на выбранную нами кетку из списка предыдущего шага, мы должны проверить по всем направления если можно захватить токены противника.

                    listOfPossibleOpponentTokens.add(opponentPlayerToken);
                }
            }

            possibleWaysForCurrentToken.put(currentPlayerToken, listOfPossibleOpponentTokens);
        }
    }

    // ArrayList<HashMap<Cell, ArrayList<Cell>>> possibleWaysToMove;

    private void checkTheLastOpponentToken() {
//        possibleWaysToMove.stream().forEach(currentPlayerToken -> currentPlayerToken.ge);
    }

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
