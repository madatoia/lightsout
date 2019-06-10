package com.lightsout.solver;

import com.lightsout.domain.integerimpl.BoardOperations;
import com.lightsout.domain.integerimpl.Puzzle;

/**
 * Class that searches for the solution of the puzzle. It uses backtracking to generate the positions on which the pieces should be applied
 * and pruning to not continue to expend a board that can no longer lead to a solution.
 */
public class Solver {

    private final BoardOperations boardOperations;

    Solver(BoardOperations boardOperations) {
        this.boardOperations = boardOperations;
    }

    /**
     * Method to solve the puzzle
     *
     * @return the result
     * @throws UnableToFindSolutionException if no solution could be computed
     */
    public int[][] solve(final Puzzle puzzle) throws UnableToFindSolutionException {

        final Result result = new Result(puzzle.getPieces().length);
        generateAllPossiblePositions(puzzle, puzzle.getInitialBoard(), 0, result);

        if (!result.isFound()) {
            throw new UnableToFindSolutionException();
        }

        return result.getPositionsToApply();
    }

    /**
     * Backtracking method that generates all the possible combinations
     */
    private void generateAllPossiblePositions(Puzzle puzzle, int[][] board, int pieceIndex, Result result) {

        final int pieceOffsetHeight = board.length - puzzle.getPieces()[pieceIndex].length;
        final int pieceOffsetWidth = board[0].length - puzzle.getPieces()[pieceIndex][0].length;

        for (int i = 0; i <= pieceOffsetHeight; i++) {
            for (int j = 0; j <= pieceOffsetWidth; j++) {
                result.addToResult(i, j, pieceIndex);

                int[][] newBoard = boardOperations.applyPieceOnBoard(puzzle, board, pieceIndex, i, j);
                if (pieceIndex == puzzle.getPieces().length - 1) {
                    if (boardOperations.isCorrect(newBoard)) {
                        result.setFound(true);
                    }
                } else if (boardOperations.canLeadToSolution(puzzle, newBoard, pieceIndex)) {
                    generateAllPossiblePositions(puzzle, newBoard, pieceIndex + 1, result);
                }

                if (result.isFound()) {
                    return;
                }
            }
        }
    }
}
