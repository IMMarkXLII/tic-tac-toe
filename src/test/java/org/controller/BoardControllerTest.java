package org.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.models.Board;
import org.models.CustomPlayer;
import org.models.Player;
import org.models.Symbol;

import java.io.*;
import java.util.Scanner;

public class BoardControllerTest extends TestCase {

    @Test
    public void testBoardControllerInitializePlayersWhenPlayer1ChoosesASymbol() {
        BoardController boardController = new BoardController(3);
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players/initialize-players-1.txt");) {
            Scanner scanner = new Scanner(inputStream);
            boardController.initializePlayers(scanner);
            Player player1 = boardController.getPlayer1();
            Player player2 = boardController.getPlayer2();
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
        BoardController boardController = new BoardController(3);
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players/initialize-players-2.txt");) {
            Scanner scanner = new Scanner(inputStream);
            boardController.initializePlayers(scanner);
            Player player1 = boardController.getPlayer1();
            Player player2 = boardController.getPlayer2();
            assertEquals("Leslie Knope", player1.getName());
            assertEquals(Symbol.X, player1.getSymbol());
            assertEquals("Ron Swanson", player2.getName());
            assertEquals(Symbol.O, player2.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBoardControllerInitializePlayersWhenPlayerSelectRobotMode() {
        BoardController boardController = new BoardController(3);
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players/initialize-players-robot.txt");) {
            Scanner scanner = new Scanner(inputStream);
            boardController.initializePlayers(scanner);
            Player player1 = boardController.getPlayer1();
            Player player2 = boardController.getPlayer2();
            assertEquals("Leslie Knope", player1.getName());
            assertEquals(Symbol.O, player1.getSymbol());
            assertEquals("Robot", player2.getName());
            assertEquals(Symbol.X, player2.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBoardControllerInitializePlayersDefaultsToX() {
        BoardController boardController = new BoardController(3);
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("initialize-players/initialize-players-3-incorrect-input.txt");) {
            Scanner scanner = new Scanner(inputStream);
            boardController.initializePlayers(scanner);
            Player player1 = boardController.getPlayer1();
            Player player2 = boardController.getPlayer2();
            assertEquals("Leslie Knope", player1.getName());
            assertEquals(Symbol.X, player1.getSymbol());
            assertEquals("Ron Swanson", player2.getName());
            assertEquals(Symbol.O, player2.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBoardControllerIsGameOnWhenAPlayerWins() {
        BoardController boardController = new BoardController(3);
        Player player = new CustomPlayer("Tom Haverford", Symbol.X);
        boardController.getBoard().getGrid()[0][0] = 1;
        boardController.getBoard().getGrid()[0][1] = 1;
        boardController.getBoard().getGrid()[0][2] = 1;
        boolean isGameOn = boardController.getBoard().isGameOn(player);
        assertFalse(isGameOn);
    }

    @Test
    public void testBoardControllerIsGameOnWhenCellsAreEmpty() {
        BoardController boardController = new BoardController(3);
        Player player = new CustomPlayer("Tom Haverford", Symbol.X);
        boardController.getBoard().getGrid()[0][0] = 1;
        boardController.getBoard().getGrid()[0][2] = 1;
        boardController.getBoard().getGrid()[1][1] = 2;
        boolean isGameOn = boardController.getBoard().isGameOn(player);
        assertTrue(isGameOn);
    }

    @Test
    public void testBoardControllerIsGameOnWhenGameIsTied() {
        BoardController boardController = new BoardController(3);
        Player player = new CustomPlayer("Tom Haverford", Symbol.X);
        Player player2 = new CustomPlayer("Mark Brendanowitz", Symbol.O);
        boardController.getBoard().getGrid()[0][0] = 1;
        boardController.getBoard().getGrid()[0][1] = 2;
        boardController.getBoard().getGrid()[0][2] = 1;

        boardController.getBoard().getGrid()[1][0] = 2;
        boardController.getBoard().getGrid()[1][1] = 2;
        boardController.getBoard().getGrid()[1][2] = 1;

        boardController.getBoard().getGrid()[2][0] = 2;
        boardController.getBoard().getGrid()[2][1] = 1;
        boardController.getBoard().getGrid()[2][2] = 2;
        // The isGameOn should return false for both players to be considered a valid test
        // in the actual in game scenario, the game will test only for the player that has played the previous turn
        boolean isGameOn1 = boardController.getBoard().isGameOn(player);
        boolean isGameOn2 = boardController.getBoard().isGameOn(player2);
        assertFalse(isGameOn1 && isGameOn2);
    }
}
