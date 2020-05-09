package org.models;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private Integer[][] board;
    private Player player1;
    private Player player2;

    public Board() {
        board = new Integer[3][3];
        for (Integer[] row : board)
            Arrays.fill(row, new Integer(0));
    }

    public void initializePlayers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1, please enter your name:");
        String player1Name = scanner.nextLine();
        System.out.println(player1Name + ", please choose your symbol 'X' or 'O', default is 'X': press return to skip");
        String player1SymbolString = scanner.nextLine();

        Symbol player2Symbol;
        switch (player1SymbolString) {
            case "O":
                System.out.println(player1Name + ", Your symbol is 'O'");
                this.player1 = new Player(player1Name, Symbol.O);
                player2Symbol = Symbol.X;
                break;
            case "X":
            default:
                System.out.println(player1Name + ", Your symbol is 'X'");
                this.player1 = new Player(player1Name, Symbol.X);
                player2Symbol = Symbol.O;
        }

        System.out.println("Player 2, please enter your name:");
        String player2Name = scanner.nextLine();
        System.out.println(player2Name + ", Your symbol is " + player2Symbol.toString());
        this.player2 = new Player(player2Name, player2Symbol);
    }

    public void startGame() {
        int moveCount = 0;
        Player firstPlayer = this.player1;
        Player secondPlayer = this.player2;
        while (moveCount < 9) {
            System.out.println(toString());
            updateBoard(firstPlayer);
            if (!gameOn(firstPlayer))
                break;
            Player tmp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = tmp;
            moveCount++;
        }

        System.out.println("The final board state is:");
        System.out.println(toString());
    }

    private void updateBoard(Player nextPlayer) {
        System.out.println(nextPlayer + ", please choose as empty cell for your next move using the numbers 1 to 9");
        Scanner scanner = new Scanner(System.in);
        int nextTile = scanner.nextInt();
        int nextIndexI = (nextTile - 1) / 3;
        int nextIndexJ = (nextTile - 1) % 3;
        if (board[nextIndexI][nextIndexJ] != 0) {
            System.out.println("Invalid move, cell is already occupied. Please re-enter");
            updateBoard(nextPlayer);
        } else
            board[nextIndexI][nextIndexJ] = nextPlayer.getSymbol().getSymbolCode();
    }

    private boolean gameOn(Player player) {
        int[][] winningCombinations = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9},
                new int[]{1, 4, 7},
                new int[]{2, 5, 8},
                new int[]{3, 6, 9},
                new int[]{1, 5, 9},
                new int[]{3, 5, 7}
        };
        int symbolCode = player.getSymbol().getSymbolCode();
        for (int i[] : winningCombinations) {
            if (board[(i[0] - 1) / 3][(i[0] - 1) % 3] == symbolCode &&
                    board[(i[1] - 1) / 3][(i[1] - 1) % 3] == symbolCode &&
                    board[(i[2] - 1) / 3][(i[2] - 1) % 3] == symbolCode) {
                System.out.println(player.getName() + " has won!");
                return false;
            }
        }
        if (Arrays.asList(board[0]).contains(0) ||
                Arrays.asList(board[1]).contains(0) ||
                Arrays.asList(board[2]).contains(0))
            return true;
        System.out.println("The game is tied!");
        return false;
    }

    @Override
    public String toString() {
        return "=====================================================\n" +
                player1 + " and " + player2 + "\n" +
                "=====================================================\n" +
                "Board is:\n" + printBoard() +
                "\n=====================================================";
    }

    private String printBoard() {
        StringBuilder boardStringBuilder = new StringBuilder("");
        for (Integer i[] : board) {
            boardStringBuilder.append("|");
            for (int j : i) {
                boardStringBuilder.append(" " + Symbol.getSymbol(j) + " |");
            }
            boardStringBuilder.append("\n");
        }
        return boardStringBuilder.toString();
    }
}