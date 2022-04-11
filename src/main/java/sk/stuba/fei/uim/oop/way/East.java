package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.controls.Move;

public class East extends Way implements Compass {
    public East(int currentPlayerY, int currentPlayerX, int opponentPlayerY, int opponentPlayerX) {
        super(currentPlayerY, currentPlayerX, opponentPlayerY, opponentPlayerX);
    }

    @Override
    public boolean checkWay() {
        return getCurrentPlayerX() == getOpponentPlayerX() - 1
                && getCurrentPlayerY() == getOpponentPlayerY();
    }

    @Override
    public Move getMove() {
        return Move.EAST;
    }
}