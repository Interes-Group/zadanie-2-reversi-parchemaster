package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.boadr.GameBoardPanel;
import sk.stuba.fei.uim.oop.boadr.InformationPanel;

import javax.swing.*;
import java.awt.*;

public class GameLogic extends JPanel{

//    private GameBoard board;

    @Getter
    @Setter
    private int boardSize;
    @Getter
    @Setter
    private GameBoardPanel gameBoardPanel;
    @Getter
    @Setter
    private InformationPanel informationPanel;



    public GameLogic(int size) {


        this.boardSize = size;

        setLayout(new BorderLayout());

        gameBoardPanel = new GameBoardPanel(size);
        informationPanel = new InformationPanel(this);

        add(gameBoardPanel, BorderLayout.NORTH);
        add(informationPanel, BorderLayout.SOUTH);


    }
}
