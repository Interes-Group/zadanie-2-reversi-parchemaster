package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.button.ColorChoseAction;
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

    @Getter
    private JLabel playerScore;
    @Getter
    private JLabel computerScore;
    @Getter
    private JLabel currentPlayerLabel;
    @Getter
    private JDialog finishDialog;
    @Getter
    private JLabel winnerLabel;
    @Getter
    private JFrame frame;
    @Getter
    private JDialog colorDialog = new JDialog();


    private Cell[][] allCells;

    public GameLogic(GameBoardPanel gameBoardPanel, JLabel playerScore, JLabel computerScore, JLabel currentPlayerLabel, JDialog finishDialog, JLabel winnerName, JFrame frame) {
        this.gameBoardPanel = gameBoardPanel;
        this.winnerLabel = winnerName;
        //WHITE
        this.player = new Player(new ArrayList<Cell>(), TokenColor.NOT_SPECIFIED, "You");
        //BLACK
        this.computer = new Player(new ArrayList<Cell>(), TokenColor.NOT_SPECIFIED, "Computer");
        this.frame = frame;



        player.setPlayerColor(TokenColor.BLACK);
        computer.setPlayerColor(TokenColor.WHITE);


        this.playerScore = playerScore;
        playerScore.setText("Player has: " + getPlayer().getPlayerTokens().size());

        this.computerScore = computerScore;
        computerScore.setText("Computer has " + getComputer().getPlayerTokens().size());

        this.currentPlayerLabel = currentPlayerLabel;

        this.finishDialog = finishDialog;


        frame.setVisible(true);
        initializeGame();

//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    public void createColorButtons() {
        // TODO create somehow chose color dialog before start of the game
        colorDialog.setLayout(new BorderLayout());
        var blackButton = new JButton("Black");
        var whiteButton = new JButton("White");
        var colorChoseAction = new ColorChoseAction(blackButton, whiteButton, player, computer, this, frame);
        blackButton.addActionListener(colorChoseAction);
        whiteButton.addActionListener(colorChoseAction);
        colorDialog.add(new JLabel("What color do you want to play?"), BorderLayout.NORTH);
        colorDialog.add(whiteButton, BorderLayout.WEST);
        colorDialog.add(blackButton, BorderLayout.EAST);
        colorDialog.setSize(300, 100);
        GameFrame.centreWindow(colorDialog);
        colorDialog.setModal(true);
        colorDialog.setVisible(true);
    }

    private void putLogicToCell() {
        for (var cellList : allCells) {
            for (var cell : cellList) {
                cell.setGameLogic(this);
            }
        }
    }

//    private void choseColor() {
//        colorDialog.setLayout(new BorderLayout());
//        var blackButton = new JButton("Black");
//        var whiteButton = new JButton("White");
//        var colorChoseAction = new ColorChoseAction(blackButton, whiteButton, player, computer);
//        blackButton.addActionListener(colorChoseAction);
//        colorDialog.add(new JLabel("What color do you want to play?"), BorderLayout.NORTH);
//        colorDialog.add(whiteButton, BorderLayout.WEST);
//        colorDialog.add(blackButton, BorderLayout.EAST);
//        colorDialog.setSize(300, 100);
//        colorDialog.setVisible(true);
//    }


    public void initializeGame() {
        this.allCells = gameBoardPanel.getAllCells();
        putLogicToCell();
        changePlayers();
        findPlayersTokens();
        start();
    }

    private void changePlayers() {
        currentPlayer = round % 2 == 0 ? player : computer;
        opponentPlayer = round % 2 == 1 ? player : computer;
        currentPlayerLabel.setText("Current player is: " + currentPlayer.getName() + " with color: " + currentPlayer.getPlayerColor());
        round++;
    }

    public void start() {
        findTheClosestCellsNearToCurrentPlayer();
        setHighlightedCells();
        isNewRound = false;
        checkFinishGame();
        //TODO there
        if (currentPlayer.equals(computer)) computerMove();
    }

    public String findWinner() {
        String winnerString;
        if (getPlayer().getPlayerTokens().size() > getComputer().getPlayerTokens().size()) {
            winnerString = getPlayer().getName() + " won tha game";
        }
        else if (getPlayer().getPlayerTokens().size() < getComputer().getPlayerTokens().size()) {
            winnerString = getComputer().getName() + " won tha game";
        }
        else {
            winnerString = "Draw won tha game\"";
        }
        return winnerString;
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

    private void checkFinishGame() {
        if (possibleCells.size() == 0) {
            winnerLabel.setText(findWinner());
            finishDialog.setVisible(true);
        }
    }

    // waiting till current player press any possible cell
//    Runnable helloRunnable = new Runnable() {
//        public void run() {
//            checkIsClicked();
//        }
//    };


    // if current player press possible cell then flip opponents tokens
    public void checkIsClicked() {
        if (possibleCells.stream().anyMatch(Cell::isClicked)) {
            specifyLostColors();
            var newToken = possibleCells.stream().filter(Cell::isClicked).findFirst().get();
            currentPlayer.getPlayerTokens().add(newToken);
            removeHighlightedCells();
            flipToOpponentTokens(newToken);
            changePlayers();
            playerScore.setText("Player has: " + getPlayer().getPlayerTokens().size());
            computerScore.setText("Computer has " + getComputer().getPlayerTokens().size());
            start();
        }
    }

    // some tokens lost their TokenColor wile program waiting, so this function specify lost tokens colors
    private void specifyLostColors() {
        player.getPlayerTokens().forEach(token -> token.setTokenColor(player.getPlayerColor()));
        computer.getPlayerTokens().forEach(token -> token.setTokenColor(computer.getPlayerColor()));
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
        var newFlipTokens = findOpponentTokens(selectedNewToken);
        for (var token : newFlipTokens) {
            token.setTokenColor(currentPlayer.getPlayerColor());
            opponentPlayer.getPlayerTokens().remove(token);
            currentPlayer.getPlayerTokens().add(token);
        }
    }

    private ArrayList<Cell> findOpponentTokens(Cell selectedNewToken) {
        var listOfWays = findListOfWays(opponentPlayer, selectedNewToken);
        var newFlipTokens = flipOpponentsTokensLogic(listOfWays);
        return newFlipTokens;
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

    private void computerMove() {
        var allPossibleOpponentFlipWay = new ArrayList<ArrayList<Cell>>();
        // find the longest possible way to flip opponent tokens
        for (var possibleCell : possibleCells) {
            possibleCell.setTokenColor(computer.getPlayerColor());
            allPossibleOpponentFlipWay.add(findOpponentTokens(possibleCell));
            possibleCell.setTokenColor(TokenColor.NOT_SPECIFIED);
        }
        var testList = allPossibleOpponentFlipWay.stream().max(Comparator.comparingInt(ArrayList::size)).get();
        var index = allPossibleOpponentFlipWay.indexOf(testList);
//        for (var cell : testList) {
//            cell.setTokenColor(computer.getPlayerColor());
//        }
        possibleCells.get(index).setTokenColor(computer.getPlayerColor());
        possibleCells.get(index).setClicked(true);
        checkIsClicked();
    }
}
