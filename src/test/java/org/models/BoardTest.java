package org.models;

import junit.framework.TestCase;
import org.junit.Test;

public class BoardTest extends TestCase {
    @Test
    public void testBoardCheckPlayerWins() {
        Board board = new Board(5, 4);
        board.updateGrid(9, 1);
        board.updateGrid(13, 1);
        board.updateGrid(17, 1);
        board.updateGrid(21, 1);
        assertTrue(board.checkWin(1, 9));
        assertTrue(board.checkWin(1, 13));
        assertTrue(board.checkWin(1, 17));
        assertTrue(board.checkWin(1, 21));
    }

    @Test
    public void testBoardCheckPlayerWins2() {
        Board board = new Board(5, 4);
        board.updateGrid(6, 1);
        board.updateGrid(12, 1);
        board.updateGrid(18, 1);
        board.updateGrid(24, 1);
        assertTrue(board.checkWin(1, 6));
        assertTrue(board.checkWin(1, 12));
        assertTrue(board.checkWin(1, 18));
        assertTrue(board.checkWin(1, 24));
    }

    @Test
    public void testBoardCheckPlayerWins3() {
        Board board = new Board(5, 4);
        board.updateGrid(6, 1);
        board.updateGrid(12, 2);
        board.updateGrid(18, 1);
        board.updateGrid(24, 1);
        for (int i = 1; i <= 25; i++)
            assertFalse(board.checkWin(1, i));
    }

    @Test
    public void testBoardCheckPlayerWins4() {
        Board board = new Board(5, 4);
        board.updateGrid(6, 1);
        board.updateGrid(12, 1);
        board.updateGrid(18, 1);
        for (int i = 1; i <= 25; i++)
            assertFalse(board.checkWin(1, i));
    }

    @Test
    public void testBoardCheckPlayerWins5() {
        Board board = new Board(5, 4);
        board.updateGrid(1, 1);
        board.updateGrid(7, 2);
        board.updateGrid(13, 1);
        board.updateGrid(19, 1);
        board.updateGrid(25, 1);
        System.out.println(board.printBoard(new CustomPlayer("A", Symbol.X), new CustomPlayer("A", Symbol.O)));
        for (int i = 1; i <= 25; i++)
            assertFalse(board.checkWin(1, i));
    }

    @Test
    public void testBoardCheckPlayerWins6() {
        Board board = new Board(3, 3);
        board.updateGrid(1, 1);
        board.updateGrid(3, 1);
        board.updateGrid(4, 2);
        board.updateGrid(5, 2);
        board.updateGrid(6, 2);
        board.updateGrid(7, 2);
        board.updateGrid(8, 1);
        board.updateGrid(9, 1);
        assertTrue(board.checkWin(2, 4));
    }

}
