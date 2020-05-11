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

    private boolean validateCellContent(int cellNumber, int value) {
        int rowIndex = (cellNumber - 1) / grid.length;
        int columnIndex = (cellNumber - 1) % grid.length;
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
        for (Integer[] row : grid)
            if (Arrays.asList(row).contains(0))
                return true;
        return false;
    }

    public Integer[][] getGrid() {
        return grid;
    }

    public String printBoard(Player player1, Player player2) {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (Integer i[] : grid) {
            boardStringBuilder.append("|");
            for (int j : i) {
                boardStringBuilder.append(" " + Symbol.getSymbol(j) + " |");
            }
            boardStringBuilder.append("\n");
        }
        return "=====================================================\n"
                + player1 + " and " + player2 + "\n"
                + "=====================================================\n"
                + "Board is:\n" + boardStringBuilder.toString()
                + "\n=====================================================";
    }

    public boolean isGameOn(Player player) {
        if (isPlayerWinning(player.getSymbol().getSymbolCode())) {
            System.out.println(player.getName() + " has won!");
            return false;
        }

        if (isAtLeastOneCellVacant())
            return true;
        System.out.println("The game is tied!");
        return false;
    }

    public boolean isPlayerWinning(int playerCode) {
        String winningStr = getWinningString(playerCode);
        List<String> diagonalL2RCells = new ArrayList<>(), diagonalR2LCells = new ArrayList<>();

        for (int iIndex = 0; iIndex < grid.length; iIndex++) {
            List<String> column = new ArrayList<>(), row = new ArrayList<>();
            for (int jIndex = 0; jIndex < grid.length; jIndex++) {
                row.add(grid[iIndex][jIndex].toString());
                column.add(grid[jIndex][iIndex].toString());
                if (isListHasWinningString(winningStr, column) || isListHasWinningString(winningStr, row))
                    return true;
                if (iIndex == jIndex) {
                    diagonalL2RCells.add(grid[iIndex][iIndex].toString());
                    diagonalR2LCells.add(grid[iIndex][grid.length - iIndex - 1].toString());
                    if (isListHasWinningString(winningStr, diagonalL2RCells) || isListHasWinningString(winningStr, diagonalR2LCells))
                        return true;
                }
            }
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
