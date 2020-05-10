package org.models;

import java.util.Scanner;

public interface Player {
    String getName();

    Symbol getSymbol();

    void updateGrid(Board board, Scanner scanner);
}
