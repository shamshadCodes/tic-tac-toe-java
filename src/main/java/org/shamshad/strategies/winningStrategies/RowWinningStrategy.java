package org.shamshad.strategies.winningStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Move;
import org.shamshad.models.Player;
import org.shamshad.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private List<Map<Symbol, Integer>> rowMaps;

    public RowWinningStrategy(int size, List<Player> players) {
        rowMaps = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            rowMaps.add(new HashMap<>());
            for (Player player: players) {
                rowMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getNewCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(
                symbol, 1 + rowMaps.get(row).get(symbol)
        );

        return rowMaps.get(row).get(symbol) == board.getDimension();
    }

    @Override
    public void undoLastMove(Move move, Board board) {
        int row = move.getNewCell().getRow();;
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(
                symbol,
                rowMaps.get(row).get(symbol) - 1
        );
    }
}
