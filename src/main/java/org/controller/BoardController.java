package org.controller;

import org.models.*;

import java.util.Scanner;

public class BoardController {

    public static int[][] winningCombinations = {
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9},
            new int[]{1, 4, 7},
            new int[]{2, 5, 8},
            new int[]{3, 6, 9},
            new int[]{1, 5, 9},
            new int[]{3, 5, 7}
    };

    private Board board;
    private Player player1;
    private Player player2;

    public BoardController() {
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void initializePlayers(Scanner scanner) throws IllegalArgumentException {

        String gameType = getPlayerInput(scanner, "Please enter R to play against a robot, any other key for two player mode.");

        String player1Name = getPlayerInput(scanner, "Player 1, please enter your name:");
        String player1SymbolString = getPlayerInput(scanner, player1Name + ", please choose your symbol 'X' or 'O', default is 'X': press return to skip");

        Symbol player2Symbol = setupPlayer1(player1Name.isEmpty() ? "Player 1" : player1Name, player1SymbolString);

        setupPlayer2(scanner, gameType, player2Symbol);
    }

    private String getPlayerInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private void setupPlayer2(Scanner scanner, String gameType, Symbol player2Symbol) {
        if ("R".equals(gameType)) {
            this.player2 = new RobotPlayer("Robot", player2Symbol);
            System.out.println("Player 2 is " + player2);
        } else {
            System.out.println("Player 2, please enter your name:");
            String player2Input = scanner.nextLine();
            String player2Name = player2Input.isEmpty() ? "Player 2" : player2Input;
            System.out.println(player2Name + ", Your symbol is " + player2Symbol.toString());
            this.player2 = new CustomPlayer(player2Name, player2Symbol);
        }
    }

    private Symbol setupPlayer1(String player1Name, String player1SymbolString) {
        Symbol player2Symbol;
        switch (player1SymbolString) {
            case "O":
                System.out.println(player1Name + ", Your symbol is 'O'");
                this.player1 = new CustomPlayer(player1Name, Symbol.O);
                player2Symbol = Symbol.X;
                break;
            default:
                System.out.println("WARNING: Invalid input, using default 'X'");
            case "X":
                System.out.println(player1Name + ", Your symbol is 'X'");
                this.player1 = new CustomPlayer(player1Name, Symbol.X);
                player2Symbol = Symbol.O;
        }
        return player2Symbol;
    }

    public void startGame(Scanner scanner) {
        Player firstPlayer = player1;
        Player secondPlayer = player2;
        while (board.isAtLeastOneCellVacant()) {
            System.out.println(board.printBoard(player1, player2));
            firstPlayer.updateGrid(board, scanner);
            if (!board.isGameOn(firstPlayer))
                break;
            Player tmp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = tmp;
        }

        System.out.println("The final board state is:");
        System.out.println(board.printBoard(player1, player2));
    }
}
