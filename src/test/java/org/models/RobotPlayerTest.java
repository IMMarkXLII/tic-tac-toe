package org.models;

import junit.framework.TestCase;
import org.controller.BoardController;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RobotPlayerTest extends TestCase {
    @Test
    public void testRobotPlayerUpgradeGrid() {
        BoardController boardController = new BoardController(3);
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("update-grid/upgrade-grid-1.txt");) {
            Player player = new RobotPlayer("Tom Haverford", Symbol.X, 0);
            Scanner scanner = new Scanner(inputStream);
            List<Integer> vacantCellsBefore = Arrays.stream(boardController.getBoard().getVacantCells()).boxed().collect(Collectors.toList());
            List<Integer> expectedVacantCells = Arrays.stream(boardController.getBoard().getVacantCells()).boxed().collect(Collectors.toList());
            player.updateGrid(boardController.getBoard(), scanner);
            List<Integer> vacantCellsAfter = Arrays.stream(boardController.getBoard().getVacantCells()).boxed().collect(Collectors.toList());

            vacantCellsBefore.removeAll(vacantCellsAfter);
            assertTrue(vacantCellsBefore.size() == 1);
            vacantCellsAfter.addAll(vacantCellsBefore);
            Collections.sort(vacantCellsAfter);
            assertEquals(expectedVacantCells, vacantCellsAfter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRobotPlayerCalculateOptimalMove() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 1; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        player.calculateOptimalMove(board, 1, 1);
        assertEquals(6, player.bestMove);
    }

    @Test
    public void testRobotPlayerCalculateOptimalMove2() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 1;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 1; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        player.calculateOptimalMove(board, 1, 1);
        assertEquals(5, player.bestMove);
    }

    @Test
    public void testRobotPlayerCalculateOptimalMove3() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 1; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 1;
        board.getGrid()[2][0] = 2; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        player.calculateOptimalMove(board, 1, 1);
        assertEquals(5, player.bestMove);
    }

    @Test
    public void testRobotPlayerCalculateOptimalMove4() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 1; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 2; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        player.calculateOptimalMove(board, 1, 1);
        assertEquals(6, player.bestMove);
    }

    @Test
    public void testRobotPlayerFindBestMove() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 1; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        RobotPlayer.Move bestMove = player.findBestMove(board, 2);
        assertEquals(6, bestMove.cell);
    }

    @Test
    public void testRobotPlayerFindBestMove2() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 1;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 1; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        RobotPlayer.Move bestMove = player.findBestMove(board, 2);
        assertEquals(4, bestMove.cell);
    }

    @Test
    public void testRobotPlayerFindBestMove3() {
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3, 3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 1; board.getGrid()[1][1] = 0; board.getGrid()[1][2] = 1;
        board.getGrid()[2][0] = 2; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;
        RobotPlayer.Move bestMove = player.findBestMove(board, 2);
        assertEquals(5, bestMove.cell);
    }

    @Test
    public void testRobotPlayerFindBestMove4(){
        RobotPlayer player = new RobotPlayer("Tom Haverford", Symbol.O, 0);

        Board board = new Board(3,3);
        board.getGrid()[0][0] = 1; board.getGrid()[0][1] = 2; board.getGrid()[0][2] = 2;
        board.getGrid()[1][0] = 0; board.getGrid()[1][1] = 1; board.getGrid()[1][2] = 0;
        board.getGrid()[2][0] = 2; board.getGrid()[2][1] = 1; board.getGrid()[2][2] = 2;

        RobotPlayer.Move bestMove = player.findBestMove(board, 2);
        assertEquals(6, bestMove.cell);
    }

}
