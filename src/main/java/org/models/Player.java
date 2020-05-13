package org.models;

import java.util.Scanner;

public interface Player {
    String getName();

    Symbol getSymbol();

    int getLastMove();

    void setLastMove(int lastMove);

    void updateGrid(Board board, Scanner scanner);
}
