package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.way.Way;
import sk.stuba.fei.uim.oop.way.WayHandler;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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


    @Getter
    @Setter
    private Player currentPlayer;
    @Getter
    @Setter
    private Player opponentPlayer;

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

    private void flipToOpponentTokens(ArrayList<Cell> tokens) {
        for (var token : tokens) {
            token.setTokenColor(currentPlayer.getPlayerColor());
            opponentPlayer.getPlayerTokens().remove(token);
//            token.setPossibleToPaint(true);
        }
    }

    private void flipOpponentsTokens(Cell selectedNewToken) {
        var listOfMoves = findListOfWays(opponentPlayer, selectedNewToken);
        // TODO there
//        var flipTokens = findTheEndOfWayForSpecificToken(listOfMoves, selectedNewToken);
        var newFlipTokens = flipOpponentsTokensLogic(listOfMoves);
        flipToOpponentTokens(newFlipTokens);

    }

    private void checkIsClicked() {
        if (possibleCells.stream().anyMatch(cell -> cell.isClicked())) {
            specificLostColors();
            var newToken = possibleCells.stream().filter(cell -> cell.isClicked()).findFirst().get();
            round ++;
            removeHighlightedCells();
            possibleCells.clear();
            flipOpponentsTokens(newToken);
            start();
        }
    }

    private void specificLostColors() {
        for (var token : player1.getPlayerTokens()) {
            token.setTokenColor(TokenColor.WHITE);
        }
        for (var token : player2.getPlayerTokens()) {
            token.setTokenColor(TokenColor.BLACK);
        }
    }

    public void start() {
        currentPlayer = round % 2 == 1 ? player1 : player2;
        opponentPlayer = round % 2 == 0 ? player1 : player2;
//        findCurrentPlayerToken(currentPlayer);
        findPlayersTokens(currentPlayer, opponentPlayer);
        findTheClosestCellsNearToCurrentPlayer(currentPlayer, opponentPlayer);
        setHighlightedCells(currentPlayer);

        isNewRound = false;
    }


    private ArrayList<Cell> flipOpponentsTokensLogic(ArrayList<Way> ways) {
        var allFlipTokens = new ArrayList<Cell>();
        for (var way : ways) {
            allFlipTokens.addAll(way.flipTokens(allCells));
        }
        return allFlipTokens;
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
            var listOfWays = findListOfWays(opponentPlayer, currentPlayerToken);
            possibleCells.addAll(findTheEndOfWayForSpecificToken(listOfWays));
        }
    }

    private ArrayList<Way> findListOfWays(Player opponentPlayer, Cell currentPlayerToken) {
        var wayHandler = new WayHandler();
        for (var opponentPlayerToken : opponentPlayer.getPlayerTokens()) {
            var newWay = new Way(
                    currentPlayerToken,
                    opponentPlayerToken
            );
            wayHandler.setHandler(newWay);
        }
        return wayHandler.getWays();
    }


    private ArrayList<Cell> findTheEndOfWayForSpecificToken(ArrayList<Way> ways) {
        var listOfCells = new ArrayList<Cell>();
        for (var way : ways) {
            listOfCells.addAll(way.findingTheEnd(allCells));
        }
        return listOfCells.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
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
}
