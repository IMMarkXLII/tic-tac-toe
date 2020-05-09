package org.tictactoe;

import org.models.Board;

public class TicTacToe
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to a game of tic-tac-toe");
        Board board = new Board();
        board.initializePlayers();
        board.startGame();
//        System.out.println(board);
    }
}
