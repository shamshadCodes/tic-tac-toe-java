package org.shamshad.controller;

import org.shamshad.exceptions.InvalidGameParamsException;
import org.shamshad.models.Game;
import org.shamshad.models.Player;
import org.shamshad.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    // Method for creating the game
    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws InvalidGameParamsException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }
}
