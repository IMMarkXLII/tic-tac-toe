package org.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    private Integer[][] grid;
    private int minWinLength;

    public Board(int size, int minWinLength) {
        this.minWinLength = minWinLength;
        this.grid = new Integer[size][size];
        for (Integer[] row : grid)
            Arrays.fill(row, new Integer(0));
    }

    private Board(Integer[][] grid, int minWinLength) {
        this.grid = grid;
        this.minWinLength = minWinLength;
    }

    public Board copy() {
        return new Board(Arrays.stream(this.grid).map(Integer[]::clone).toArray(Integer[][]::new), this.minWinLength);
    }

    public boolean isCellOccupied(int cellNumber) {
        int rowIndex = (cellNumber - 1) / grid.length;
        int columnIndex = (cellNumber - 1) % grid.length;
        return this.grid[rowIndex][columnIndex] != 0;
    }

    public void updateGrid(int cellNumber, int value) {
        int rowIndex = (cellNumber - 1) / grid.length;
        int columnIndex = (cellNumber - 1) % grid.length;
        this.grid[rowIndex][columnIndex] = value;
    }

    public int[] getVacantCells() {
        Integer[] array = Stream.of(grid)
                .flatMap(Stream::of).toArray(Integer[]::new);
        return IntStream.range(1, array.length + 1)
                .filter(i -> array[i - 1] == 0)
                .toArray();
    }

    public boolean hasGridSpace() {
        for (Integer[] row : grid)
            if (Arrays.asList(row).contains(0))
                return true;
        return false;
    }

    public Integer[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return getBoardStringBuilder().toString();
    }

    public String printBoard(Player player1, Player player2) {
        StringBuilder boardStringBuilder = getBoardStringBuilder();
        return "=====================================================\n"
                + player1 + " and " + player2 + "\n"
                + "=====================================================\n"
                + "Board is:\n" + boardStringBuilder.toString()
                + "\n=====================================================";
    }

    private StringBuilder getBoardStringBuilder() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (Integer i[] : grid) {
            boardStringBuilder.append("|");
            for (int j : i) {
                boardStringBuilder.append(" " + Symbol.getSymbol(j) + " |");
            }
            boardStringBuilder.append("\n");
        }
        return boardStringBuilder;
    }

    public boolean isGameOn(Player player) {
        if (player.getLastMove() < 0)
            return true;
        if (hasPlayerWon(player.getSymbol().getSymbolCode(), player.getLastMove())) {
            System.out.println(player.getName() + " has won!");
            return false;
        }

        if (hasGridSpace())
            return true;
        System.out.println("The game is tied!");
        return false;
    }

    public boolean hasPlayerWon(int code, int lastCell) {
        String winningStr = getWinningString(code);

        int rowIndex = (lastCell - 1) / grid.length;
        int columnIndex = (lastCell - 1) % grid.length;

        //verify only the row where last update was made
        List<String> row = new ArrayList<>();
        for (int i = rowIndex, j = 0; j < grid.length; j++) {
            row.add(grid[i][j].toString());
            if (isListHasWinningString(winningStr, row))
                return true;
        }

        //verify only the column where last update was made
        List<String> column = new ArrayList<>();
        for (int i = 0, j = columnIndex; i < grid.length; i++) {
            column.add(grid[i][j].toString());
            if (isListHasWinningString(winningStr, column))
                return true;
        }

        //verify descending diagonal for the last cell entered
        int minIndex = Math.min(rowIndex, columnIndex);
        List<String> descendingDiagonal = new ArrayList<>();
        for (int i = rowIndex - minIndex, j = columnIndex - minIndex; i < grid.length && j < grid.length; i++, j++) {
            descendingDiagonal.add(grid[i][j].toString());
            if (isListHasWinningString(winningStr, descendingDiagonal))
                return true;
        }

        //verify ascending diagonal for the last cell entered
        int maxPossibleMovement = Math.min(grid.length - 1 - rowIndex, columnIndex);
        List<String> ascendingDiagonal = new ArrayList<>();
        for (int i = rowIndex + maxPossibleMovement, j = columnIndex - maxPossibleMovement; i >= 0 && j < grid.length; i--, j++) {
            ascendingDiagonal.add(grid[i][j].toString());
            if (isListHasWinningString(winningStr, ascendingDiagonal))
                return true;
        }

        return false;
    }

    private boolean isListHasWinningString(String winningStr, List<String> column) {
        return String.join("", column).contains(winningStr);
    }

    private String getWinningString(int playerCode) {
        String[] winningCombination = new String[this.minWinLength];
        Arrays.fill(winningCombination, Integer.toString(playerCode));
        return String.join("", winningCombination);
    }
}
