package org.models;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomPlayer implements Player {
    private String name;
    private Symbol symbol;
    private int lastMove;

    public int getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }
    public CustomPlayer(String name, Symbol symbol) {
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
        double maxCellNumber = Math.pow(board.getGrid().length, 2);
        System.out.println(this + ", please choose as empty cell for your next move using the numbers 1 to " + maxCellNumber);
        int nextCell;
        try {
            String nextLine = scanner.nextLine();
            nextCell = Integer.parseInt(nextLine);
            if (nextCell < 1 || nextCell > maxCellNumber)
                throw new InputMismatchException();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid move, value is invalid(Invalid number or outside of range 1-" + maxCellNumber + "). Please re-enter");
            updateGrid(board, scanner);
            return;
        }

        if (board.isCellOccupied(nextCell)) {
            System.out.println("Invalid move, cell is already occupied. Please re-enter");
            updateGrid(board, scanner);
        } else {
            board.updateGrid(nextCell, this.getSymbol().getSymbolCode());
            this.lastMove = nextCell;
        }
    }

    @Override
    public String toString() {
        return name + " is playing " + symbol;
    }
}
