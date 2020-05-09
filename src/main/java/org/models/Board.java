package org.models;

import java.util.Arrays;

public class Board {
    private Integer[][] grid;
    private Player player1;
    private Player player2;

    public Integer[][] getGrid() {
        return grid;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board() {
        grid = new Integer[3][3];
        for (Integer[] row : grid)
            Arrays.fill(row, new Integer(0));
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
        for (Integer i[] : grid) {
            boardStringBuilder.append("|");
            for (int j : i) {
                boardStringBuilder.append(" " + Symbol.getSymbol(j) + " |");
            }
            boardStringBuilder.append("\n");
        }
        return boardStringBuilder.toString();
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
