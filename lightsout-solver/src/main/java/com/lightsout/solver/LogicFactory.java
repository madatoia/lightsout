package com.lightsout.solver;

import com.lightsout.domain.BoardOperationsFactory;
import com.lightsout.domain.PuzzleType;
import com.lightsout.domain.integerimpl.BoardOperations;

/**
 * Factory method that creates the {@link Checker} and {@link Solver} with the appropriate {@link BoardOperations}
 */
public class LogicFactory {

    private final BoardOperationsFactory boardOperationsFactory;

    public LogicFactory() {
        this.boardOperationsFactory = new BoardOperationsFactory();
    }

    /**
     * Creates a solver with the appropriate boards operations. The board operations is being determined based on the puzzleType
     */
    public Solver createSolver(PuzzleType puzzleType) {
        return new Solver(boardOperationsFactory.createBoardOperations(puzzleType));
    }

    /**
     * Creates a checker with the appropriate boards operations. The board operations is being determined based on the puzzleType
     */

    public Checker createChecker(PuzzleType puzzleType) {
        return new Checker(boardOperationsFactory.createBoardOperations(puzzleType));
    }
}
