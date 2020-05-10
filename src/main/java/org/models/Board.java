package org.models;

import org.controller.BoardController;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    private Integer[][] grid;

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

    public String printBoard(Player player1, Player player2) {
        StringBuilder boardStringBuilder = new StringBuilder("");
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
        int symbolCode = player.getSymbol().getSymbolCode();
        for (int[] combo : BoardController.winningCombinations) {
            if (isAWinningCombo(symbolCode, combo)) {
                System.out.println(player.getName() + " has won!");
                return false;
            }
        }

        if (isAtLeastOneCellVacant())
            return true;
        System.out.println("The game is tied!");
        return false;
    }

    private boolean isAWinningCombo(int symbolCode, int[] index) {
        return validateCellContent(index[0], symbolCode)
                && validateCellContent(index[1], symbolCode)
                && validateCellContent(index[2], symbolCode);
    }
}
