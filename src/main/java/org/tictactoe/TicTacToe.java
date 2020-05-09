package org.tictactoe;

import org.controller.BoardController;

import java.io.InputStream;
import java.util.Scanner;

public class TicTacToe {
    BoardController boardController;
    InputStream inputStream;

    public TicTacToe(InputStream inputStream) {
        this.boardController = new BoardController();
        this.inputStream = inputStream;
    }

    public void start() {
        Scanner scanner = new Scanner(inputStream);
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
