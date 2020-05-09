package org.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.models.Board;
import org.models.Player;
import org.models.Symbol;

import java.io.*;

public class BoardControllerTest extends TestCase {

    @Test(expected = Test.None.class)
    public void testBoardControllerInitializePlayersWhenPlayer1ChoosesASymbol() {
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players-1.txt");) {
            boardController.initializePlayers(inputStream);
            Board board = boardController.getBoard();
            Player player1 = board.getPlayer1();
            Player player2 = board.getPlayer2();
            assertEquals("Leslie Knope", player1.getName());
            assertEquals(Symbol.O, player1.getSymbol());
            assertEquals("Ron Swanson", player2.getName());
            assertEquals(Symbol.X, player2.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBoardControllerInitializePlayersWhenPlayer1ChoosesDefaultSymbol() {
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players-2.txt");) {
            boardController.initializePlayers(inputStream);
            Board board = boardController.getBoard();
            Player player1 = board.getPlayer1();
            Player player2 = board.getPlayer2();
            assertEquals("Leslie Knope", player1.getName());
            assertEquals(Symbol.X, player1.getSymbol());
            assertEquals("Ron Swanson", player2.getName());
            assertEquals(Symbol.O, player2.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardControllerInitializePlayersFails() {
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players-3-incorrect-input.txt");) {
            boardController.initializePlayers(inputStream);
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
