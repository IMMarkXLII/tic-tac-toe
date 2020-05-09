package org.tictactoe;

import org.controller.BoardController;

import java.io.InputStream;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to a game of tic-tac-toe");
        BoardController boardController = new BoardController();
        InputStream inputStream = System.in;
        boardController.start(inputStream);
    }
}
