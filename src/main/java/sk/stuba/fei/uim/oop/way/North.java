package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class North extends Way implements Compass, CellFinder {
    public North(Cell currentToken, Cell opponentToken) {
        super(currentToken, opponentToken);
    }

    @Override
    public boolean checkWay() {
        return getCurrentToken().getPositionX() == getOpponentToken().getPositionX()
                && getCurrentToken().getPositionY() == getOpponentToken().getPositionY() + 1;
    }

    @Override
    public Move getMove() {
        return Move.NORTH;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        var listOfCells = new ArrayList<Cell>();
        for (int y = getCurrentToken().getPositionY() - 2; y >= 0; y--) {
            if (allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                    && !allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                listOfCells.add(allCells[y][getCurrentToken().getPositionX()]);
                break;
            }
            else if (allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                break;
            }
        }
        return listOfCells;
    }

    @Override
    public ArrayList<Cell> flipTokens(Cell[][] allCells) {
        var allFlipTokens = new ArrayList<Cell>();
        var possibleTokens = new ArrayList<Cell>();
        for (int y = getCurrentToken().getPositionY() - 1; y >= 0; y--) {
            if (!allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                    &&!allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                possibleTokens.add(allCells[y][getCurrentToken().getPositionX()]);
            }
            else if (allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                allFlipTokens.addAll(possibleTokens);
                possibleTokens.clear();
                break;
            }
            else if (allCells[y][getCurrentToken().getPositionX()].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                possibleTokens.clear();
                break;
            }
        }
        return allFlipTokens;
    }
}
