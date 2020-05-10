package org.models;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomPlayer implements Player {
    private String name;
    private Symbol symbol;

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
        System.out.println(this + ", please choose as empty cell for your next move using the numbers 1 to 9");
        int nextCell;
        try {
            String nextLine = scanner.nextLine();
            nextCell = Integer.parseInt(nextLine);
            if (nextCell < 1 || nextCell > 9)
                throw new InputMismatchException();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid move, value is invalid(Invalid number or outside of range 1-9). Please re-enter");
            updateGrid(board, scanner);
            return;
        }

        if (board.isCellOccupied(nextCell)) {
            System.out.println("Invalid move, cell is already occupied. Please re-enter");
            updateGrid(board, scanner);
        } else
            board.updateGrid(nextCell, this.getSymbol().getSymbolCode());
    }

    @Override
    public String toString() {
        return name + " is playing " + symbol;
    }
}
