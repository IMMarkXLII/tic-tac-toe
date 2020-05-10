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
        int[] emptyCells = board.getVacantCells();
        Random random = new Random();
        int nextCell = emptyCells[random.nextInt(emptyCells.length)];
        board.updateGrid(nextCell, symbol.getSymbolCode());
    }

    @Override
    public String toString() {
        return name + ":" + symbol;
    }
}
