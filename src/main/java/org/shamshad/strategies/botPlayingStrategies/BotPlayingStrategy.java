package org.shamshad.strategies.botPlayingStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
