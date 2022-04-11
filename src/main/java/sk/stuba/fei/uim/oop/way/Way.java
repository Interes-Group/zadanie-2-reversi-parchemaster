package sk.stuba.fei.uim.oop.way;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.controls.Move;

import java.util.ArrayList;

public class Way implements Compass{

    @Setter
    @Getter
    private int currentPlayerY;
    @Setter
    @Getter
    private int currentPlayerX;
    @Setter
    @Getter
    private int opponentPlayerY;
    @Setter
    @Getter
    private int opponentPlayerX;

    public Way(int currentPlayerY, int currentPlayerX, int opponentPlayerY, int opponentPlayerX) {
        this.currentPlayerY = currentPlayerY;
        this.currentPlayerX = currentPlayerX;
        this.opponentPlayerY = opponentPlayerY;
        this.opponentPlayerX = opponentPlayerX;

    }

    @Override
    public boolean checkWay() {
        return false;
    }

    @Override
    public Move getMove() {
        return null;
    }
}
