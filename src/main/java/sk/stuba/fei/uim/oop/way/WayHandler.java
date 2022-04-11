package sk.stuba.fei.uim.oop.way;

import lombok.Getter;
import sk.stuba.fei.uim.oop.controls.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WayHandler {
    private Way way;
    @Getter
    private ArrayList<Move> moves = new ArrayList<>();


    public WayHandler() {
    }

    public void setHandler(Way way) {
        this.way = way;
        getAcceptableWays(createListOfPossibleWays());
    }

    private void getAcceptableWays(ArrayList<Way> allPossibleWays) {
        moves.addAll(allPossibleWays.stream().filter(Way::checkWay).map(Way::getMove).collect(Collectors.toList()));
    }

    private ArrayList<Way> createListOfPossibleWays() {
        var listOfWays = new ArrayList<Way>();
        listOfWays.add(new North(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new NorthEast(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new East(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new SouthEast(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new South(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new SouthWest(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new West(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        listOfWays.add(new NorthWest(
                way.getCurrentPlayerY(),
                way.getCurrentPlayerX(),
                way.getOpponentPlayerY(),
                way.getOpponentPlayerX()
        ));
        return listOfWays;
    }
}
