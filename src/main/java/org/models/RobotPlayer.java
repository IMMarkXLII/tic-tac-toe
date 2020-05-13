package org.models;

import java.util.Scanner;

public class RobotPlayer implements Player {
    private String name;
    private Symbol symbol;
    private int lastMove;

    public RobotPlayer(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public void updateGrid(Board board, Scanner scanner) {
        int otherPlayerSymbol = symbol.getSymbolCode() == 1 ? 2 : 1;
        calculateOptimalMove(board, otherPlayerSymbol, 1);
        board.updateGrid(bestMove, symbol.getSymbolCode());
        this.lastMove = bestMove;
        bestMove = bestMoveScore = -1;
        bestMoveLevel = 1;
    }

    int bestMove = -1;
    int bestMoveScore = -1;
    int bestMoveLevel = Integer.MAX_VALUE;

    public void calculateOptimalMove(Board board, int otherPlayerSymbol, int level) {
        for (Integer vacantCell : board.getVacantCells()) {
            Board newBoard = board.copy();
            newBoard.updateGrid(vacantCell, symbol.getSymbolCode());
            if (newBoard.checkWin(symbol.getSymbolCode(), vacantCell)) {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 1);
                }
            } else if (newBoard.isAtLeastOneCellVacant()) {
                checkOppositePlayersMoves(otherPlayerSymbol, level, vacantCell, newBoard);
            } else {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 0);
                }
            }
        }
    }

    private void setStats(int level, Integer bestMove, int moveScore) {
        this.bestMove = bestMove;
        this.bestMoveScore = moveScore;
        this.bestMoveLevel = level;
    }

    private void checkOppositePlayersMoves(int otherPlayerSymbol, int level, Integer vacantCell, Board newBoard) {
        for (Integer vacant : newBoard.getVacantCells()) {
            Board player2Board = newBoard.copy();
            player2Board.updateGrid(vacant, otherPlayerSymbol);
            if (player2Board.checkWin(otherPlayerSymbol, vacant)) {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacant, 2);
                }
            } else if (player2Board.isAtLeastOneCellVacant()) {
                calculateOptimalMove(player2Board, otherPlayerSymbol, level + 1);
            } else {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 0);
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + ":" + symbol;
    }
}
