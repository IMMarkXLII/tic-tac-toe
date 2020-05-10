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
        int otherPlayerSymbol = symbol.getSymbolCode() == 1 ? 2 : 1;
        calculateOptimalMove(board, otherPlayerSymbol, 1);
        int nextCell = bestMoveScore != -1 ? bestMove : randomNextMove(board);
        board.updateGrid(nextCell, symbol.getSymbolCode());
        bestMove = bestMoveScore = -1;
        bestMoveLevel = 1;
    }

    private int randomNextMove(Board board) {
        int[] emptyCells = board.getVacantCells();
        Random random = new Random();
        return emptyCells[random.nextInt(emptyCells.length)];
    }

    int bestMove = -1;
    int bestMoveScore = -1;
    int bestMoveLevel = -1;

    public void calculateOptimalMove(Board board, int otherPlayerSymbol, int level) {
        for (Integer vacantCell : board.getVacantCells()) {
            Board newBoard = new Board(board.getGridCopy());
            newBoard.updateGrid(vacantCell, symbol.getSymbolCode());

            if (newBoard.isPlayerWinning(symbol.getSymbolCode())) {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    bestMove = vacantCell; bestMoveScore = 1; bestMoveLevel = level;
                }
            } else if (newBoard.isAtLeastOneCellVacant()) {

                for (Integer vacant : newBoard.getVacantCells()) {
                    Board player2Board = new Board(newBoard.getGridCopy());
                    player2Board.updateGrid(vacant, otherPlayerSymbol);
                    if (player2Board.isPlayerWinning(otherPlayerSymbol)) {
                        if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                            bestMove = vacant; bestMoveScore = 2; bestMoveLevel = level;
                        }
                    } else if (player2Board.isAtLeastOneCellVacant()) {
                        calculateOptimalMove(player2Board, otherPlayerSymbol, level + 1);
                    } else {
                        if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                            bestMove = vacantCell; bestMoveScore = 0; bestMoveLevel = level;
                        }
                    }
                }
            } else {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    bestMove = vacantCell; bestMoveScore = 0; bestMoveLevel = level;
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + ":" + symbol;
    }
}
