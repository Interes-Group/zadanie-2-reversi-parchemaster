package sk.stuba.fei.uim.oop.way;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.Move;

public class North extends Way implements Compass{
    public North(int currentPlayerY, int currentPlayerX, int opponentPlayerY, int opponentPlayerX) {
        super(currentPlayerY, currentPlayerX, opponentPlayerY, opponentPlayerX);
    }

    @Override
    public boolean checkWay() {
        return getCurrentPlayerX() == getOpponentPlayerX()
                && getCurrentPlayerY() == getOpponentPlayerY() + 1;
    }

    @Override
    public Move getMove() {
        return Move.NORTH;
    }
}
