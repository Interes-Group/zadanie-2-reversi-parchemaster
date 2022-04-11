package sk.stuba.fei.uim.oop.way;

import sk.stuba.fei.uim.oop.controls.Move;

public class SouthWest extends Way implements Compass{
    public SouthWest(int currentPlayerY, int currentPlayerX, int opponentPlayerY, int opponentPlayerX) {
        super(currentPlayerY, currentPlayerX, opponentPlayerY, opponentPlayerX);
    }

    @Override
    public boolean checkWay() {
        return getCurrentPlayerX() == getOpponentPlayerX() + 1
                && getCurrentPlayerY() == getOpponentPlayerY() - 1;
    }

    @Override
    public Move getMove() {
        return Move.SOUTH_WEST;
    }
}