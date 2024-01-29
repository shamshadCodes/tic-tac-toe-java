package org.shamshad.models;

import org.shamshad.exceptions.InvalidGameParamsException;
import org.shamshad.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private int playerTurnIndex;
    private List<Move> moves;
    private Player winner;
    private GameStatus gameStatus;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.playerTurnIndex = 0;
        this.moves = new ArrayList<>();
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


        private boolean valid() {
            if (this.players.size() < 2) {
                return false;
            }

            if (this.players.size() != this.dimension - 1) {
                return false;
            }

            int botCount = 0;

            for (Player player : this.players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount += 1;
                }
            }

            if (botCount >= 2) {
                return false;
            }

            Set<Character> existingSymbols = new HashSet<>();

            for (Player player : players) {
                if (existingSymbols.contains(player.getSymbol().getCharacter())) {
                    return false;
                }

                existingSymbols.add(player.getSymbol().getCharacter());
            }

            return true;
        }

        public Game build() throws InvalidGameParamsException {
            if (!valid()) {
                throw new InvalidGameParamsException("Invalid params for game");
            }
            return new Game(
                    dimension, players, winningStrategies
            );
        }
    }
}
