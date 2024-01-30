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

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.playerTurnIndex = 0;
        this.moves = new ArrayList<>();
//        this.winner = winner;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    public void setPlayerTurnIndex(int playerTurnIndex) {
        this.playerTurnIndex = playerTurnIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printResult() {
        if (gameStatus.equals(GameStatus.CONCLUDED)) {
            System.out.println("Game has concluded.");
            System.out.println("Winner is: " + winner.getName());
        } else {
            System.out.println("Game is draw");
        }
    }

    public void printBoard() {
        this.board.print();
    }

    private boolean validateMove(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();

        if (row < 0 || col < 0 || row >= board.getDimension() || col >= board.getDimension()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.FREE);
    }

    public void makeMove() {
        Player currentPlayer = players.get(playerTurnIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn.");


        Cell proposedCell = currentPlayer.makeMove(board);

        System.out.println("Move made at row: " + proposedCell.getRow() +
                " col: " + proposedCell.getCol() + ".");

        if (!validateMove(proposedCell)) {
            System.out.println("Invalid move. Retry.");
            return;
        }

        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellState(CellState.OCCUPIED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);

        if (checkGameWon(currentPlayer, move)) return;

        if (checkDraw()) return;

        playerTurnIndex += 1;
        playerTurnIndex %= players.size();
    }

    private boolean checkDraw() {
        if (moves.size() == board.getDimension() * board.getDimension()) {
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWon(Player currentPlayer, Move move) {
        for (WinningStrategy winningStrategy: winningStrategies) {
            if (winningStrategy.checkWinner(move, board)) {
                gameStatus = GameStatus.CONCLUDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
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
                throw new InvalidGameParamsException("Invalid parameters for game. Please try again!");
            }
            return new Game(
                    dimension, players, winningStrategies
            );
        }
    }
}
