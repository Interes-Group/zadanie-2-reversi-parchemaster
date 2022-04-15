package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.boadr.Cell;
import java.util.ArrayList;

public interface CellFinder {
    ArrayList<Cell> findingTheEnd(Cell[][] allCells);
    ArrayList<Cell> flipTokens(Cell[][] allCells);
}
