package sk.stuba.fei.uim.oop.player;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.events.MouseEvent;
import sk.stuba.fei.uim.oop.boadr.Cell;
import sk.stuba.fei.uim.oop.boadr.TokenColor;

public class Player {
    @Setter
    @Getter
    private Cell[][] playerCells;
    private int countCell;
    @Setter
    @Getter
    private TokenColor playerColor;

    public Player(Cell[][] playerCells, int countCell, TokenColor playerColor) {
        this.playerColor = playerColor;
        this.playerCells = playerCells;
        this.countCell = countCell;
    }

    private void clickMouseOnCell(){
        for (int i = 0; i < playerCells.length; i++) {
            for (int j = 0; j < playerCells.length; j++) {

            }
        }
    }
}
