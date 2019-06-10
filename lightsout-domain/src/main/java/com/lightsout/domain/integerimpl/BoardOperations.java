package com.lightsout.domain.integerimpl;

public class BoardOperations {

    public int[][] copyBoard(int[][] board) {
        int boardHeight = board.length;
        int boardWidth = board[0].length;

        int[][] newBoard = new int[boardHeight][boardWidth];

        for (int lineIndex = 0; lineIndex < boardHeight; lineIndex++) {
            System.arraycopy(board[lineIndex], 0, newBoard[lineIndex], 0, boardWidth);
        }
        return newBoard;
    }

    /**
     * Applies a piece on the board on the specified position
     *
     * @param puzzle     the puzzle containing the depth and the pieces
     * @param board      the bord on which to apply the piece
     * @param pieceIndex the piece to apply
     * @param posX       x coord of the position where the piece should be applied
     * @param posY       y coord of the position where the piece should be applied
     * @return the resulting board
     */
    public int[][] applyPieceOnBoard(Puzzle puzzle, int[][] board, int pieceIndex, int posX, int posY) {

        int[][] newBoard = copyBoard(board);
        int[][] piece = puzzle.getPieces()[pieceIndex];
        for (int i = posX; i < piece.length + posX; i++) {
            for (int j = posY; j < piece[0].length + posY; j++) {
                newBoard[i][j] = (newBoard[i][j] + piece[i - posX][j - posY]) % puzzle.getDepth();
            }
        }
        return newBoard;
    }

    /**
     * Computes the cell complement for the integer implementation. The complement represents the number of 1 to be
     * added so that the cell becomes 0.
     *
     * @param puzzle containing the depth information
     * @param value  the value for which the complement will be computed
     *
     * @return the complement
     */
    public int computeCellComplement(Puzzle puzzle, int value) {
        return value == 0 ? value : puzzle.getDepth() - (value % puzzle.getDepth());
    }

    /**
     * Checks that the current state can still lead to a solution. This is being verified by making sure that the amount
     * of 1s in the remaining pieces is at least equal with the amount of switches still needed to fill in the board. Also,
     * the difference between the board complement and the sumOfPiecesLeft should be dividable by the depth;
     *
     * @param board      the board
     * @param pieceIndex the number of pieces applied up until now
     * @return true if the state can still lead to a valid solution and false otherwise
     */
    public boolean canLeadToSolution(Puzzle puzzle, final int[][] board, final int pieceIndex) {
        final int boardComplement = computeBoardComplement(puzzle, board);
        final int sumOfPiecesLeft = puzzle.getSumOfPiecesFromPos(pieceIndex);

        return boardComplement <= sumOfPiecesLeft && (sumOfPiecesLeft - boardComplement) % puzzle.getDepth() == 0;
    }

    /**
     * Computes the sum of the cells of the board's complement
     */
    public int computeBoardComplement(Puzzle puzzle, int[][] board) {
        int sum = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell != 0) {
                    sum += computeCellComplement(puzzle, cell);
                }
            }
        }
        return sum;
    }

    /**
     * Verifies if all the cells of the board are 0
     */
    public boolean isCorrect(int[][] board) {
        for (int[] line : board) {
            for (int cell : line) {
                if (cell != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
