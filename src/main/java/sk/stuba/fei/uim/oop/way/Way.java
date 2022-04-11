package sk.stuba.fei.uim.oop.way;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.controls.CellFinder;

import java.util.ArrayList;

public class Way implements Compass, CellFinder {

    @Getter
    private Cell currentToken;
    @Getter
    private Cell opponentToken;


    public Way(Cell currentToken, Cell opponentToken) {
        this.currentToken = currentToken;
        this.opponentToken = opponentToken;
    }

    @Override
    public boolean checkWay() {
        return false;
    }

    @Override
    public Move getMove() {
        return null;
    }

    @Override
    public ArrayList<Cell> findingTheEnd(Cell[][] allCells) {
        return null;
    }

    @Override
    public ArrayList<Cell> flipTokens(Cell[][] allCells) {
        return null;
    }
}
