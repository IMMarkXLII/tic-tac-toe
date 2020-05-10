package org.models;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    private Integer[][] grid;
    private Player player1;
    private Player player2;

    public Board() {
        grid = new Integer[3][3];
        for (Integer[] row : grid)
            Arrays.fill(row, new Integer(0));
    }

    public boolean isCellOccupied(int cellNumber) {
        int rowIndex = (cellNumber - 1) / 3;
        int columnIndex = (cellNumber - 1) % 3;
        return this.grid[rowIndex][columnIndex] != 0;
    }

    public void updateGrid(int cellNumber, int value) {
        int rowIndex = (cellNumber - 1) / 3;
        int columnIndex = (cellNumber - 1) % 3;
        this.grid[rowIndex][columnIndex] = value;
    }

    public boolean validateCellContent(int cellNumber, int value) {
        int rowIndex = (cellNumber - 1) / 3;
        int columnIndex = (cellNumber - 1) % 3;
        return this.grid[rowIndex][columnIndex] == value;
    }

    public int[] getVacantCells() {
        Integer[] array = Stream.of(grid)
                .flatMap(Stream::of).toArray(Integer[]::new);
        return IntStream.range(1, array.length + 1)
                .filter(i -> array[i - 1] == 0)
                .toArray();
    }

    public boolean isAtLeastOneCellVacant() {
        return Arrays.asList(grid[0]).contains(0)
                || Arrays.asList(grid[1]).contains(0)
                || Arrays.asList(grid[2]).contains(0);
    }

    public Integer[][] getGrid() {
        return grid;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return "=====================================================\n"
                + player1 + " and " + player2 + "\n"
                + "=====================================================\n"
                + "Board is:\n" + printBoard()
                + "\n=====================================================";
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
}
