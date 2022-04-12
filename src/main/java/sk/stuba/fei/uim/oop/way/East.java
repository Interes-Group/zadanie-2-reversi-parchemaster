package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class East extends Way implements Compass, CellFinder {
    public East(Cell currentToken, Cell opponentToken) {
        super(currentToken, opponentToken);
    }

    @Override
    public boolean checkWay() {
        return getCurrentToken().getPositionX() == getOpponentToken().getPositionX() - 1
                && getCurrentToken().getPositionY() == getOpponentToken().getPositionY();
    }

    @Override
    public Move getMove() {
        return Move.EAST;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        try {

            var listOfCells = new ArrayList<Cell>();
            for (int x = getCurrentToken().getPositionX() + 2; x < allCells.length; x++) {
                if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                        && !allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                    listOfCells.add(allCells[getCurrentToken().getPositionY()][x]);
                    break;

                } else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                    break;
                }
            }
            return listOfCells;
        }
        catch (Exception e) {
            System.out.println("there");
            return null;
        }
    }

    @Override
    public ArrayList<Cell> flipTokens(Cell[][] allCells) {
        try {

            var allFlipTokens = new ArrayList<Cell>();
            var possibleTokens = new ArrayList<Cell>();
            for (int x = getCurrentToken().getPositionX() + 1; x < allCells.length; x++) {
                if (!allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                        && !allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                    possibleTokens.add(allCells[getCurrentToken().getPositionY()][x]);
                } else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                    allFlipTokens.addAll(possibleTokens);
                    possibleTokens.clear();
                    break;
                } else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                    possibleTokens.clear();
                    break;
                }
            }
            return allFlipTokens;
        }
        catch (Exception e) {
            System.out.println("there");
            return null;
        }
    }
}