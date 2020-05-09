package org.tictactoe;

import org.controller.BoardController;

import java.io.InputStream;
import java.util.Scanner;

public final class TicTacToe {
    private BoardController boardController;
    private InputStream inputStream;

    public TicTacToe(InputStream stream) {
        this.boardController = new BoardController();
        this.inputStream = stream;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void start() {
        Scanner scanner = new Scanner(this.inputStream);
        this.boardController.initializePlayers(scanner);
        this.boardController.startGame(scanner);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to a game of tic-tac-toe");
        InputStream inputStream = System.in;
        TicTacToe ticTacToe = new TicTacToe(inputStream);
        ticTacToe.start();
    }
}
