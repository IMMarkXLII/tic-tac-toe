package org.models;


public enum Symbol {
    X(1),
    O(2);

    private final int symbolCode;

    Symbol(int symbol) {
        this.symbolCode = symbol;
    }

    public int getSymbolCode() {
        return this.symbolCode;
    }

    public static String getSymbol(int symbolCode) {
        if (symbolCode == 1)
            return Symbol.X.toString();
        else if (symbolCode == 2)
            return Symbol.O.toString();
        return " ";
    }
}
