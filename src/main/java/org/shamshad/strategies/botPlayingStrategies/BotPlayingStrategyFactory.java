package org.shamshad.strategies.botPlayingStrategies;

import org.shamshad.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficulty(BotDifficultyLevel difficultyLevel) {
        return new EasyBotPlayingStrategy(); //Playing level defaulted to Easy for now

//        return switch (difficultyLevel) {
//        case EASY -> new EasyBotPlayingStrategy();
//        case MEDIUM -> new MediumBotPlayingStrategy();
//        case HARD -> new HardBotPlayingStrategy();
    }
}
