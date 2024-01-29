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

        int dimension = 3;
        List<Player> players = List.of(
                new Player("John Doe", new Symbol('X'), PlayerType.HUMAN),
                new BotPlayer("Computer", new Symbol('O'), BotDifficultyLevel.EASY)
        );
        List<WinningStrategy> winningStrategies = List.of(
        );


        try{
            game = gameController.createGame(dimension, players, winningStrategies);
        } catch (InvalidGameParamsException e) {
            System.out.println("Seems like the input parameters are incorrect. Please try again!");
            return;
        }
    }
}