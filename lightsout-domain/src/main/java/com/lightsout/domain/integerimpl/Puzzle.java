package com.lightsout.domain.integerimpl;

/**
 * Class containing the problem's initial state. In the end solution will be built here.
 */
public class Puzzle {

    private final int[][] initialBoard;
    private final int depth;
    private final int[][][] pieces;

    private int[] sumOfPiecesAfterPos;

    public Puzzle(int[][] board, int[][][] pieces, int depth) {
        this.pieces = pieces;
        this.depth = depth;
        this.initialBoard = board;
    }

    public void initialize() {
        this.sumOfPiecesAfterPos = computePartialSums(pieces);
    }

    public int getDepth() {
        return depth;
    }

    public int[][][] getPieces() {
        return pieces;
    }

    public int[][] getInitialBoard() {
        return initialBoard;
    }

    /**
     * Returns the sum of all the 1s in the pieces after the index provided
     */
    public int getSumOfPiecesFromPos(int pieceIndex) {
        return sumOfPiecesAfterPos[pieceIndex];
    }

    /**
     * Computes partial sums of all the ones of the pieces left after the index.
     */
    private int[] computePartialSums(int[][][] pieces) {
        int size = pieces.length;
        int[] partialSums = new int[size];
        //last sum is 0
        for (int index = size - 2; index >= 0; index--) {
            partialSums[index] = partialSums[index + 1] + computeSum(pieces[index + 1]);
        }
        return partialSums;
    }

    private int computeSum(int[][] piece) {
        int sum = 0;

        for (int[] row : piece) {
            for (int cell : row) {
                sum += cell;
            }
        }
        return sum;
    }

}
