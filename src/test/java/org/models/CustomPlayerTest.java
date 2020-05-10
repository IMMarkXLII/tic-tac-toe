package org.models;

import junit.framework.TestCase;
import org.controller.BoardController;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CustomPlayerTest extends TestCase {
    @Test
    public void testCustomPlayerUpgradeGrid() {
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("update-grid/upgrade-grid-1.txt");) {
            Player player = new CustomPlayer("Tom Haverford", Symbol.X);
            Scanner scanner = new Scanner(inputStream);
            player.updateGrid(boardController.getBoard(), scanner);
            assertEquals(Integer.valueOf(1), boardController.getBoard().getGrid()[0][0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCustomPlayerUpgradeGridWhenUserChoosesIncorrectOption() {
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("update-grid/upgrade-grid-2.txt");) {
            Player player = new CustomPlayer("Tom Haverford", Symbol.X);
            Scanner scanner = new Scanner(inputStream);
            player.updateGrid(boardController.getBoard(), scanner);
            assertEquals(Integer.valueOf(1), boardController.getBoard().getGrid()[0][1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
