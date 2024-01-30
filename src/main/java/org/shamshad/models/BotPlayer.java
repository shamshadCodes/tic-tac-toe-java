package org.shamshad.models;

import org.shamshad.strategies.botPlayingStrategies.BotPlayingStrategy;
import org.shamshad.strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficulty(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
