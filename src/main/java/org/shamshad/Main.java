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

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            // printing the board
            gameController.displayBoard(game);

            // Check if someone wants to undo the last move
            System.out.println("Does anyone want to undo? (y/n)");
            String undoRequired = scanner.next();

            if (!game.getMoves().isEmpty() && undoRequired.equalsIgnoreCase("y")) {
                gameController.undo(game);
            } else {
                if(!game.getMoves().isEmpty()){
                    System.out.println("First move of the game. Undo not possible!");
                }
                // move next player
                gameController.makeMove(game);
            }
        }
    }
}