package org.tictactoe;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TicTacToeTest extends TestCase {

    @Test
    public void testTicTacToe() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("tic-tac-toe/tic-tac-toe-1.txt")) {
            TicTacToe ticTacToe = new TicTacToe(inputStream);
            ticTacToe.start();
            Integer[][] expectedGrid = {
                    {1, 2, 2},
                    {2, 1, 1},
                    {2, 1, 1},
            };

            assertTrue(Arrays.deepEquals(expectedGrid, ticTacToe.boardController.getBoard().getGrid()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testTicTacToeTiedGame() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("tic-tac-toe/tic-tac-toe-tied-game.txt")) {
            TicTacToe ticTacToe = new TicTacToe(inputStream);
            ticTacToe.start();
            Integer[][] expectedGrid = {
                    {1, 2, 1},
                    {2, 2, 1},
                    {2, 1, 2},
            };

            assertTrue(Arrays.deepEquals(expectedGrid, ticTacToe.boardController.getBoard().getGrid()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
