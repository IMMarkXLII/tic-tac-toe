package org.controller;

import org.models.Board;
import org.models.Player;
import org.models.Symbol;

import java.io.InputStream;
import java.util.InputMismatchException;
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

        System.out.println("Player 1, please enter your name:");
        String player1Name = scanner.nextLine();
        System.out.println(player1Name + ", please choose your symbol 'X' or 'O', default is 'X': press return to skip");
        String player1SymbolString = scanner.nextLine();

        Symbol player2Symbol;
        switch (player1SymbolString) {
            case "O":
                System.out.println(player1Name + ", Your symbol is 'O'");
                board.setPlayer1(new Player(player1Name, Symbol.O));
                player2Symbol = Symbol.X;
                break;
            case "X":
            case "":
                System.out.println(player1Name + ", Your symbol is 'X'");
                board.setPlayer1(new Player(player1Name, Symbol.X));
                player2Symbol = Symbol.O;
                break;
            default:
                throw new IllegalArgumentException();
        }

        System.out.println("Player 2, please enter your name:");
        String player2Name = scanner.nextLine();
        System.out.println(player2Name + ", Your symbol is " + player2Symbol.toString());
        board.setPlayer2(new Player(player2Name, player2Symbol));
    }

    public void startGame(Scanner scanner) {
        int moveCount = 0;
        Player firstPlayer = board.getPlayer1();
        Player secondPlayer = board.getPlayer2();
        while (moveCount < 9) {
            System.out.println(board.toString());
            updateGrid(firstPlayer, scanner);
            if (!isGameOn(firstPlayer))
                break;
            Player tmp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = tmp;
            moveCount++;
        }

        System.out.println("The final board state is:");
        System.out.println(board.toString());
    }

    public void updateGrid(Player currentPlayer, Scanner scanner) {
        System.out.println(currentPlayer + ", please choose as empty cell for your next move using the numbers 1 to 9");
        int nextCell = -1;
        try {
            String nextLine = scanner.nextLine();
            nextCell = Integer.parseInt(nextLine);
            if (nextCell < 1 || nextCell > 9)
                throw new InputMismatchException();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid move, value is invalid(Invalid number or outside of range 1-9). Please re-enter");
            updateGrid(currentPlayer, scanner);
            return;
        }

        if (board.isCellOccupied(nextCell)) {
            System.out.println("Invalid move, cell is already occupied. Please re-enter");
            updateGrid(currentPlayer, scanner);
            return;
        } else
            board.updateGrid(nextCell, currentPlayer.getSymbol().getSymbolCode());
    }

    public boolean isGameOn(Player player) {
        int symbolCode = player.getSymbol().getSymbolCode();
        for (int i[] : winningCombinations) {
            if (board.validateCellContent(i[0], symbolCode) &&
                    board.validateCellContent(i[1], symbolCode) &&
                    board.validateCellContent(i[2], symbolCode)) {
                System.out.println(player.getName() + " has won!");
                return false;
            }
        }

        if (board.isAtLeastOneCellVacant())
            return true;
        System.out.println("The game is tied!");
        return false;
    }
}
