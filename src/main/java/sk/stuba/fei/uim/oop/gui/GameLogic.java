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

public class GameLogic {

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

    @Getter
    private Player player;
    @Getter
    private Player computer;

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

    public GameLogic(GameBoardPanel gameBoardPanel) {

        this.gameBoardPanel = gameBoardPanel;

        //WHITE
        this.player = new Player(new ArrayList<Cell>(), TokenColor.WHITE, "You");
        //BLACK
        this.computer = new Player(new ArrayList<Cell>(), TokenColor.BLACK, "Computer");

        initializeGame();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    public void initializeGame() {
        this.allCells = gameBoardPanel.getAllCells();
        changePlayers();
        findPlayersTokens();
        start();
    }

    private void changePlayers() {
        currentPlayer = round % 2 == 1 ? player : computer;
        opponentPlayer = round % 2 == 0 ? player : computer;
        round++;
    }

    public void start() {
        findTheClosestCellsNearToCurrentPlayer();
        setHighlightedCells();
        isNewRound = false;
    }

    private void findPlayersTokens() {
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

    private void findTheClosestCellsNearToCurrentPlayer() {
        for (var currentPlayerToken : currentPlayer.getPlayerTokens()) {
            var listOfWays = findListOfWays(opponentPlayer, currentPlayerToken);
            //TODO if listOfWays == null => finish the game
            possibleCells.addAll(findTheEndOfWayForSpecificToken(listOfWays));
        }
        possibleCells = possibleCells.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Cell> findTheEndOfWayForSpecificToken(ArrayList<Way> ways) {
        var listOfCells = new ArrayList<Cell>();
        for (var way : ways) {
            listOfCells.addAll(way.findingTheEnd(allCells));
        }
        return listOfCells.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    private void setHighlightedCells() {
        for (var cell : possibleCells) {
            cell.setHighlighted(true);
            cell.setPossibleTokenColor(currentPlayer.getPlayerColor());
            cell.highlightCell();
        }
    }

    // waiting till current player press any possible cell
    Runnable helloRunnable = new Runnable() {
        public void run() {
            checkIsClicked();
        }
    };

    // if current player press possible cell then flip opponents tokens
    private void checkIsClicked() {
        if (possibleCells.stream().anyMatch(Cell::isClicked)) {
            specifyLostColors();
            var newToken = possibleCells.stream().filter(Cell::isClicked).findFirst().get();
            currentPlayer.getPlayerTokens().add(newToken);
            removeHighlightedCells();
            flipToOpponentTokens(newToken);
            changePlayers();
            start();
        }
    }

    // some tokens lost their TokenColor wile program waiting, so this function specify lost tokens colors
    private void specifyLostColors() {
        player.getPlayerTokens().forEach(token -> token.setTokenColor(TokenColor.WHITE));
        computer.getPlayerTokens().forEach(token -> token.setTokenColor(TokenColor.BLACK));
    }

    private void removeHighlightedCells() {
        possibleCells.forEach(cell -> {
            cell.setHighlighted(false);
            cell.setPossibleTokenColor(TokenColor.NOT_SPECIFIED);
            cell.removeHighlightCell();
        });
        possibleCells.clear();
    }

    private void flipToOpponentTokens(Cell selectedNewToken) {
        var listOfMoves = findListOfWays(opponentPlayer, selectedNewToken);
        var newFlipTokens = flipOpponentsTokensLogic(listOfMoves);
        for (var token : newFlipTokens) {
            token.setTokenColor(currentPlayer.getPlayerColor());
            opponentPlayer.getPlayerTokens().remove(token);
            currentPlayer.getPlayerTokens().add(token);
        }
    }

    private ArrayList<Way> findListOfWays(Player opponentPlayer, Cell currentPlayerToken) {
        try {

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
        catch (Exception e) {
            System.out.println("There");
            return null;
        }
    }

    private ArrayList<Cell> flipOpponentsTokensLogic(ArrayList<Way> ways) {
        var allFlipTokens = new ArrayList<Cell>();
        for (var way : ways) {
            allFlipTokens.addAll(way.flipTokens(allCells));
        }
        return allFlipTokens;
    }
}
