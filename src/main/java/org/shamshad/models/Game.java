package org.shamshad.models;

import org.shamshad.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> playerList;
    private int playerTurnIndex;
    private List<Move> moveList;
    private Player winner;
    private GameStatus gameStatus;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.playerList = playerList;
        this.playerTurnIndex = 0;
        this.moveList = new ArrayList<>();
//        this.winner = winner;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder() {
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() {
            return new Game(dimension, players, winningStrategies);
        }
    }
}
