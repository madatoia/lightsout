package com.lightsout.app;

import com.lightsout.domain.PuzzleType;
import com.lightsout.domain.PuzzleFactory;
import com.lightsout.domain.integerimpl.Puzzle;
import com.lightsout.io.FileReader;
import com.lightsout.io.OutputWriter;
import com.lightsout.solver.Checker;
import com.lightsout.solver.LogicFactory;
import com.lightsout.solver.Solver;
import com.lightsout.solver.UnableToFindSolutionException;

import java.io.IOException;
import java.util.List;

/**
 * Main class that triggers the application. Should be ran with the desired file input as argument.
 * Internally there are 2 implementation of the board (integer and binary), defined in {@link PuzzleType}.
 * In order to switch between the 2, variable puzzleType has to be modified in the main method.
 */
public class LightsOutRunner {

    public static void main(String[] args) {

        final FileReader fileReader = new FileReader();
        final PuzzleFactory puzzleFactory = new PuzzleFactory();

        final LogicFactory logicFactory = new LogicFactory();
        final OutputWriter outputWriter = new OutputWriter();
        final PuzzleType puzzleType = PuzzleType.BINARY_IMPLEMENTATION;

        try {
            final List<String> content = fileReader.getFile("samples/" + args[0]);
            Puzzle puzzle = puzzleFactory.createPuzzle(content, puzzleType);

            final Solver solver = logicFactory.createSolver(puzzleType);
            final Checker checker = logicFactory.createChecker(puzzleType);


            int[][] result = solver.solve(puzzle);
            outputWriter.writeResult(result);

            System.out.println("Checking");
            checker.check(puzzle, result);

        } catch (IOException e) {
            System.out.println("Unable to find resources file");
        } catch (UnableToFindSolutionException e) {
            System.out.println("Unable to find solution");
        }
    }
}
