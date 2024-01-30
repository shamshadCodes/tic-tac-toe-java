package org.shamshad.strategies.winningStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Move;
import org.shamshad.models.Player;
import org.shamshad.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{

    private final Map<Symbol, Integer> leftDiagonalMap;
    private final Map<Symbol, Integer> rightDiagonalMap;

    public DiagonalWinningStrategy(List<Player> players) {
        leftDiagonalMap = new HashMap<>();
        rightDiagonalMap = new HashMap<>();

        for (Player player: players) {
            leftDiagonalMap.put(player.getSymbol(), 0);
            rightDiagonalMap.put(player.getSymbol(), 0);
        }
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (row == col) {
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) + 1);
        }

        if (row + col == board.getDimension() - 1) {
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) + 1);
        }

        if (row == col) {
            if (leftDiagonalMap.get(symbol) == board.getDimension()) {
                return true;
            }
        }

        if (row + col == board.getDimension() - 1) {
            return rightDiagonalMap.get(symbol) == board.getDimension();
        }

        return false;
    }

    @Override
    public void undoLastMove(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (row == col) {
            leftDiagonalMap.put(
                    symbol, leftDiagonalMap.get(symbol) - 1
            );
        }

        if (row + col == board.getDimension() - 1) {
            rightDiagonalMap.put(
                    symbol, rightDiagonalMap.get(symbol) - 1
            );
        }
    }
}
