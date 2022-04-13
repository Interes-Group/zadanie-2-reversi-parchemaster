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
    @Getter
    private int countCell;
    @Setter
    @Getter
    private TokenColor playerColor;

    @Getter
    private String name;

    public Player(ArrayList<Cell> playerCells, TokenColor playerColor, String name) {
        this.name = name;
        this.playerColor = playerColor;
        this.playerTokens = playerCells;
        this.countCell = playerTokens.size();
    }

}
