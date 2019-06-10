package com.lightsout.solver;

import com.lightsout.domain.integerimpl.BoardOperations;
import com.lightsout.domain.integerimpl.Puzzle;

/**
 * Service that checks that the outputted result is correct
 */
public class Checker {

    private final BoardOperations boardOperations;

    Checker(BoardOperations boardOperations) {
        this.boardOperations = boardOperations;
    }

    /**
     * Checks that if the pieces are applied on the board at the positions specified in the result,
     * then each cell of the board becomes 0. It prints in the console if the result is correct or not
     *
     * @param puzzle containing the initial board and the pieces
     * @param result the result to be checked
     */
    public void check(Puzzle puzzle, int[][] result) {
        final int[][] board = applyPiecesOnBoardAccordingToResult(puzzle, result);
        System.out.println(boardOperations.isCorrect(board) ? "Solution is correct" :
                "Solution is WRONG");
    }

    /**
     * Applies all the pieces as specified in the result
     *
     * @param puzzle containing the initial board and the pieces
     * @param result the result to be checked
     * @return the resulting board
     */
    private int[][] applyPiecesOnBoardAccordingToResult(Puzzle puzzle, int[][] result) {
        int[][] board = puzzle.getInitialBoard();

        for (int step = 0; step < result.length; step++) {
            board = boardOperations.applyPieceOnBoard(puzzle, board, step, result[step][0], result[step][1]);
        }

        return board;
    }
}
