package org.tictactoe;

import org.controller.BoardController;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class TicTacToe {
    private BoardController boardController;
    private InputStream inputStream;

    public TicTacToe(InputStream stream) {

        this.inputStream = stream;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void start() {
        Scanner scanner = new Scanner(this.inputStream);
        int gridSize = getGridSize(scanner);
        this.boardController = new BoardController(gridSize);
        this.boardController.initializePlayers(scanner);
        this.boardController.startGame(scanner);
    }

    private int getGridSize(Scanner scanner) {
        System.out.println("Please enter a valid grid size, 3 or any subsequent odd number");
        String nextLine = scanner.nextLine();
        int gridSize;
        try {
            gridSize = Integer.parseInt(nextLine);
            if (gridSize < 3 || gridSize % 2 == 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException | InputMismatchException e) {
            return getGridSize(scanner);
        }
        return gridSize;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to a game of tic-tac-toe");
        InputStream inputStream = System.in;
        TicTacToe ticTacToe = new TicTacToe(inputStream);
        ticTacToe.start();
    }
}
