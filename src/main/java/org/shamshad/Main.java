package org.shamshad;

import org.shamshad.controller.GameController;
import org.shamshad.exceptions.InvalidGameParamsException;
import org.shamshad.models.*;
import org.shamshad.strategies.winningStrategies.ColumnWinningStrategy;
import org.shamshad.strategies.winningStrategies.DiagonalWinningStrategy;
import org.shamshad.strategies.winningStrategies.RowWinningStrategy;
import org.shamshad.strategies.winningStrategies.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Creating the game
        GameController gameController = new GameController();
        Game game; //initiated below

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------- Game is starting --------------");

        // Input player's name
        String playerName = scanner.next();

        int dimension = 3;
        List<Player> players = List.of(
                new Player(playerName, new Symbol('X'), PlayerType.HUMAN),
                new BotPlayer("Computer", new Symbol('O'), BotDifficultyLevel.EASY)
        );
        List<WinningStrategy> winningStrategies = List.of(
                new ColumnWinningStrategy(dimension, players),
                new RowWinningStrategy(dimension, players),
                new DiagonalWinningStrategy(players)
        );

        try{
            game = gameController.createGame(dimension, players, winningStrategies);
        } catch (InvalidGameParamsException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("This is how board looks like:");
        // print board
        gameController.displayBoard(game);
        // print if undo
        System.out.println("Does anyone want to undo? (y/n)");
        // if yes -> call undo
        String input = scanner.next();

        if (input.equalsIgnoreCase("y")) {
            gameController.undo(game);
        } else {
            // move next player
            gameController.makeMove(game);
        }
    }
}