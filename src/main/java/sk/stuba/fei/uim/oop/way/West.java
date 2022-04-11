package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class West extends Way implements Compass, CellFinder {
    public West(Cell currentToken, Cell opponentToken) {
        super(currentToken, opponentToken);
    }

    @Override
    public boolean checkWay() {
        return getCurrentToken().getPositionX() == getOpponentToken().getPositionX() + 1
                && getCurrentToken().getPositionY() == getOpponentToken().getPositionY();
    }

    @Override
    public Move getMove() {
        return Move.WEST;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        var listOfCells = new ArrayList<Cell>();
        for (int x = getCurrentToken().getPositionX() - 2; x >= 0; x--) {
            if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                    && !allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                listOfCells.add(allCells[getCurrentToken().getPositionY()][x]);
                break;
            }
            else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())){
                break;
            }
        }
        return listOfCells;
    }

    @Override
    public ArrayList<Cell> flipTokens(Cell[][] allCells) {
        var allFlipTokens = new ArrayList<Cell>();
        var possibleTokens = new ArrayList<Cell>();
        for (int x = getCurrentToken().getPositionX() - 1; x >= 0; x--) {
            if (!allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)
                    && (!allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor()))) {
                possibleTokens.add(allCells[getCurrentToken().getPositionY()][x]);
            }
            else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(getCurrentToken().getTokenColor())) {
                allFlipTokens.addAll(possibleTokens);
                possibleTokens.clear();
                break;
            }
            else if (allCells[getCurrentToken().getPositionY()][x].getTokenColor().equals(TokenColor.NOT_SPECIFIED)) {
                possibleTokens.clear();
                break;
            }
        }
        return allFlipTokens;
    }
}