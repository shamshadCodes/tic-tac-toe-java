package org.shamshad.strategies.winningStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Move;
import org.shamshad.models.Player;
import org.shamshad.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private final List<Map<Symbol, Integer>> columnMaps;

    public ColumnWinningStrategy(int dimension, List<Player> players) {
        columnMaps = new ArrayList<>();
        for (int i = 0; i < dimension; ++i) {
            columnMaps.add(new HashMap<>());
            for (Player player: players) {
                columnMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        columnMaps.get(col).put(
                symbol, 1 + columnMaps.get(col).get(symbol)
        );

        return columnMaps.get(col).get(symbol) == board.getDimension();
    }

    @Override
    public void undoLastMove(Move move, Board board) {
        int col = move.getCell().getCol();;
        Symbol symbol = move.getPlayer().getSymbol();

        columnMaps.get(col).put(
                symbol,
                columnMaps.get(col).get(symbol) - 1
        );
    }
}
