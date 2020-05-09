package org.models;

import java.util.Scanner;

public class Board {
    private int[][] board;
    private Player player1;
    private Player player2;

    public Board() {
        board = new int[3][3];
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
        while (moveCount < 9 && gameOn()) {
            System.out.println(toString());
            updateBoard(firstPlayer);

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

    private boolean gameOn() {
        return true;
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
        for (int i[] : board) {
            boardStringBuilder.append("|");
            for (int j : i) {
                boardStringBuilder.append(" " + Symbol.getSymbol(j) + " |");
            }
            boardStringBuilder.append("\n");
        }
        return boardStringBuilder.toString();
    }
}
