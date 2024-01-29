package org.shamshad.strategies.winningStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move, Board board);

    void undoLastMove(Move move, Board board);
}
