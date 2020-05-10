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
        BoardController boardController = new BoardController();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("update-grid/upgrade-grid-1.txt");) {
            Player player = new RobotPlayer("Tom Haverford", Symbol.X);
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

}
