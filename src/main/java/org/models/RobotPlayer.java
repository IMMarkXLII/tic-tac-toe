package org.models;

import java.util.Random;
import java.util.Scanner;

public class RobotPlayer implements Player {
    private String name;
    private Symbol symbol;

    public RobotPlayer(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public void updateGrid(Board board, Scanner scanner) {
        int nextCell = calculateNextMove(board);
        board.updateGrid(nextCell, symbol.getSymbolCode());
    }

    private int calculateNextMove(Board board) {
        int[] emptyCells = board.getVacantCells();
        Random random = new Random();
        return emptyCells[random.nextInt(emptyCells.length)];
    }

    private int calculateOptimalMove(Board board) {
        return -1;
    }

    @Override
    public String toString() {
        return name + ":" + symbol;
    }
}
