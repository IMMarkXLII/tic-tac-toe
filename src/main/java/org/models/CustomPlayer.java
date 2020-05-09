package org.models;

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
    public String toString() {
        return name + ":" + symbol;
    }
}
