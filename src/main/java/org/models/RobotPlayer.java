package org.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RobotPlayer implements Player {
    private String name;
    private Symbol symbol;
    private int lastMove;
    private int difficultyLevel;

    public RobotPlayer(String name, Symbol symbol, int difficultyLevel) {
        this.name = name;
        this.symbol = symbol;
        this.difficultyLevel = difficultyLevel;
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
        System.out.println("Please wait, the computer is playing!");
        int move;
        if (new Random(11).nextInt() < difficultyLevel){
            Move nextMove = findBestMove(board, this.symbol.getSymbolCode(), 0);
            move = nextMove != null ? nextMove.cell : randomNextMove(board);
        }
        else
            move = randomNextMove(board);
        board.updateGrid(move, symbol.getSymbolCode());
        this.lastMove = move;
    }

    @Deprecated
    int bestMove = -1;
    @Deprecated
    int bestMoveScore = -1;
    @Deprecated
    int bestMoveLevel = Integer.MAX_VALUE;

    @Deprecated
    public void calculateOptimalMove(Board board, int otherPlayerSymbol, int level) {
        for (Integer vacantCell : board.getVacantCells()) {
            Board newBoard = board.copy();
            newBoard.updateGrid(vacantCell, symbol.getSymbolCode());
            if (newBoard.hasPlayerWon(symbol.getSymbolCode(), vacantCell)) {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 1);
                }
            } else if (newBoard.hasGridSpace()) {
                checkOppositePlayersMoves(otherPlayerSymbol, level, vacantCell, newBoard);
            } else {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 0);
                }
            }
        }
    }

    @Deprecated
    private void setStats(int level, Integer bestMove, int moveScore) {
        this.bestMove = bestMove;
        this.bestMoveScore = moveScore;
        this.bestMoveLevel = level;
    }

    @Deprecated
    private void checkOppositePlayersMoves(int otherPlayerSymbol, int level, Integer vacantCell, Board newBoard) {
        for (Integer vacant : newBoard.getVacantCells()) {
            Board player2Board = newBoard.copy();
            player2Board.updateGrid(vacant, otherPlayerSymbol);
            if (player2Board.hasPlayerWon(otherPlayerSymbol, vacant)) {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacant, 2);
                }
            } else if (player2Board.hasGridSpace()) {
                calculateOptimalMove(player2Board, otherPlayerSymbol, level + 1);
            } else {
                if (bestMoveScore < 1 && (level <= bestMoveLevel || bestMoveLevel == -1)) {
                    setStats(level, vacantCell, 0);
                }
            }
        }
    }

    private int getOtherPlayerCode(int playerCode) {
        return playerCode == 1 ? 2 : 1;
    }

    class Move implements Comparable<Move> {
        int cell;
        int score;

        public Move(int cell, int score) {
            this.cell = cell;
            this.score = score;
        }

        @Override
        public int compareTo(Move o) {
            return this.score - o.score;
        }
    }

    private int randomNextMove(Board board) {
        int[] emptyCells = board.getVacantCells();
        Random random = new Random();
        return emptyCells[random.nextInt(emptyCells.length)];
    }

    public Move findBestMove(Board board, int playerCode, int depth) {
        List<Move> moves = new ArrayList<>();
        boolean isPlayerRobot = playerCode == this.symbol.getSymbolCode();
        for (int vacant : board.getVacantCells()) {
            Board copyBoard = board.copy();
            copyBoard.updateGrid(vacant, playerCode);
            if (copyBoard.hasPlayerWon(playerCode, vacant)) {
                int moveScore = isPlayerRobot ? 1 : -1;
                moves.add(new Move(vacant, moveScore));
            } else if (copyBoard.hasGridSpace()) {
                Move bestMove = depth == 4 ? null : findBestMove(copyBoard, getOtherPlayerCode(playerCode), depth + 1);
                if (bestMove != null)
                    moves.add(new Move(vacant, bestMove.score));
                else
                    moves.add(new Move(vacant, 0));
            } else {
                moves.add(new Move(vacant, 0));
            }
        }

        if (isPlayerRobot) {
            Collections.sort(moves, Collections.reverseOrder());
        } else {
            Collections.sort(moves);
        }

        return moves.isEmpty() ? null : moves.get(0);
    }

    @Override
    public String toString() {
        return name + ":" + symbol;
    }
}
