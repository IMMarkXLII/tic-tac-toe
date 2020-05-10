package org.controller;

import org.models.*;

import java.util.Scanner;

public class BoardController {

    private static int[][] winningCombinations = {
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

    public BoardController() {
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void initializePlayers(Scanner scanner) throws IllegalArgumentException {

        String gameType = getPlayerInput(scanner, "Please enter R to play against a robot, any other key for two player mode.");

        String player1Name = getPlayerInput(scanner, "Player 1, please enter your name:");
        String player1SymbolString = getPlayerInput(scanner, player1Name + ", please choose your symbol 'X' or 'O', default is 'X': press return to skip");

        Symbol player2Symbol = setupPlayer1(player1Name, player1SymbolString);

        setupPlayer2(scanner, gameType, player2Symbol);
    }

    private String getPlayerInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private void setupPlayer2(Scanner scanner, String gameType, Symbol player2Symbol) {
        if ("R".equals(gameType)) {
            RobotPlayer player2 = new RobotPlayer("Robot", player2Symbol);
            board.setPlayer2(player2);
            System.out.println("Player 2 is " + player2);
        } else {
            System.out.println("Player 2, please enter your name:");
            String player2Name = scanner.nextLine();
            System.out.println(player2Name + ", Your symbol is " + player2Symbol.toString());
            board.setPlayer2(new CustomPlayer(player2Name, player2Symbol));
        }
    }

    private Symbol setupPlayer1(String player1Name, String player1SymbolString) {
        Symbol player2Symbol;
        switch (player1SymbolString) {
            case "O":
                System.out.println(player1Name + ", Your symbol is 'O'");
                board.setPlayer1(new CustomPlayer(player1Name, Symbol.O));
                player2Symbol = Symbol.X;
                break;
            case "X":
            case "":
                System.out.println(player1Name + ", Your symbol is 'X'");
                board.setPlayer1(new CustomPlayer(player1Name, Symbol.X));
                player2Symbol = Symbol.O;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return player2Symbol;
    }

    public void startGame(Scanner scanner) {
        int moveCount = 0;
        Player firstPlayer = board.getPlayer1();
        Player secondPlayer = board.getPlayer2();
        while (moveCount < 9) {
            System.out.println(board);
            firstPlayer.updateGrid(board, scanner);
            if (!isGameOn(firstPlayer))
                break;
            Player tmp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = tmp;
            moveCount++;
        }

        System.out.println("The final board state is:");
        System.out.println(board);
    }

    public boolean isGameOn(Player player) {
        int symbolCode = player.getSymbol().getSymbolCode();
        for (int[] combo : winningCombinations) {
            if (isAWinningCombo(symbolCode, combo)) {
                System.out.println(player.getName() + " has won!");
                return false;
            }
        }

        if (board.isAtLeastOneCellVacant())
            return true;
        System.out.println("The game is tied!");
        return false;
    }

    private boolean isAWinningCombo(int symbolCode, int[] index) {
        return board.validateCellContent(index[0], symbolCode)
                && board.validateCellContent(index[1], symbolCode)
                && board.validateCellContent(index[2], symbolCode);
    }
}
