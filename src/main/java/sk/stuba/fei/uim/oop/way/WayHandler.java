package sk.stuba.fei.uim.oop.way;

import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WayHandler {
    private Way way;
//    @Getter
//    private ArrayList<Move> moves = new ArrayList<>();
    @Getter
    private ArrayList<Way> ways = new ArrayList<>();

    public WayHandler() {
    }

    public void setHandler(Way way) {
        this.way = way;
        getAcceptableWays();
    }

    private void getAcceptableWays() {
//        moves.addAll(allPossibleWays.stream().filter(Way::checkWay).map(Way::getMove).collect(Collectors.toList()));
        for (var way : createListOfPossibleWays()) {
            if (way.checkWay()) {
                ways.add(way);
            }
        }
    }

    private ArrayList<Way> createListOfPossibleWays() {
        var listOfWays = new ArrayList<Way>();
        listOfWays.add(new North(
                way.getCurrentToken(),
                way.getOpponentToken()

        ));
        listOfWays.add(new NorthEast(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new East(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new SouthEast(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new South(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new SouthWest(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new West(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        listOfWays.add(new NorthWest(
                way.getCurrentToken(),
                way.getOpponentToken()
        ));
        return listOfWays;
    }
}
