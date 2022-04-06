package sk.stuba.fei.uim.oop.player;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;

import java.util.ArrayList;

public class Player {
    @Setter
    @Getter
    private ArrayList<Cell> playerTokens;
    private int countCell;
    @Setter
    @Getter
    private TokenColor playerColor;

    public Player(ArrayList<Cell> playerCells, int countCell, TokenColor playerColor) {
        this.playerColor = playerColor;
        this.playerTokens = playerCells;
        this.countCell = countCell;
    }

}
