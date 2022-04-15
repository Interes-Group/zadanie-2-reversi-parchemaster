package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class NorthWest extends Way implements Compass, CellFinder {
    public NorthWest(Cell currentToken, Cell opponentToken) {
        super(currentToken, opponentToken);
    }

    @Override
    public boolean checkWay() {
        return getCurrentToken().getPositionX() == getOpponentToken().getPositionX() + 1
                && getCurrentToken().getPositionY() == getOpponentToken().getPositionY() + 1;
    }

    @Override
    public Move getMove() {
        return Move.NORTH_WEST;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        try {


            var listOfCells = new ArrayList<Cell>();
            int y = getCurrentToken().getPositionY() - 2;
                for (int x = getCurrentToken().getPositionX() - 2; x >= 0 && y >= 0; x--) {
                    if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                            && !allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        listOfCells.add(allCells[y][x]);
                        y = 0;
                        break;
                    } else if (allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        y = 0;
                        break;
                    } else {
                        y--;
                        if (isOutOfRange(y, allCells.length)) break;
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
            int y = getCurrentToken().getPositionY() - 1;
                for (int x = getCurrentToken().getPositionX() - 1; x >= 0 && y >= 0; x--) {
                    if (!allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                            && !allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        possibleTokens.add(allCells[y][x]);
                        y--;
                    } else if (allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        allFlipTokens.addAll(possibleTokens);
                        possibleTokens.clear();
                        y = 0;
                        break;
                    } else if (allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                        possibleTokens.clear();
                        y = 0;
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