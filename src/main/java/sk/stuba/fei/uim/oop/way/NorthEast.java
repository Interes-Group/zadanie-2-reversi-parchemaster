package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class NorthEast extends Way implements Compass, CellFinder {
    public NorthEast(Cell currentToken, Cell opponentToken) {
        super(currentToken, opponentToken);
    }

    @Override
    public boolean checkWay() {
        return getCurrentToken().getPositionX() == getOpponentToken().getPositionX() - 1
                && getCurrentToken().getPositionY() == getOpponentToken().getPositionY() + 1;
    }

    @Override
    public Move getMove() {
        return Move.NORTH_EAST;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        try {


            var listOfCells = new ArrayList<Cell>();
            for (int y = getCurrentToken().getPositionY() - 2; y >= 0; y--) {
                for (int x = getCurrentToken().getPositionX() + 2; x < allCells.length; x++) {
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
                for (int x = getCurrentToken().getPositionX() + 1; x < allCells.length && y >= 0; x++) {
                    if (!allCells[y][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                            && !allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        possibleTokens.add(allCells[y][x]);
                        y--;
                    } else if (allCells[y][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                        allFlipTokens.addAll(possibleTokens);
                        y = 0;
                        possibleTokens.clear();
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