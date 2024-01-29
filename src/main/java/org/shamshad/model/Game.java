package org.shamshad.model;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> playerList;
    private int playerTurnIndex;
    private List<Move> moveList;
    private Player Winner;
    private GameStatus gameStatus;
}
