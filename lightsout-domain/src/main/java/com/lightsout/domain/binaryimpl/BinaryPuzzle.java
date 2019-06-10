package com.lightsout.domain.binaryimpl;

import com.lightsout.domain.integerimpl.Puzzle;

public class BinaryPuzzle extends Puzzle {

    private int[][] binaryBoard;


    public BinaryPuzzle(Puzzle puzzle) {
        super(puzzle.getInitialBoard(), puzzle.getPieces(), puzzle.getDepth());
    }

    public void initialize() {
        super.initialize();
        binaryBoard = createBinaryForm(super.getInitialBoard());
    }

    private int[][] createBinaryForm(int[][] initialBoard) {
        int boardHeight = initialBoard.length;
        int boardWidth = initialBoard[0].length;

        int[][] binaryBoard = new int[boardHeight][boardWidth];

        for (int rowIndex = 0; rowIndex < boardHeight; rowIndex++) {
            for (int colIndex = 0; colIndex < boardWidth; colIndex++) {
                if (initialBoard[rowIndex][colIndex] != 0) {
                    binaryBoard[rowIndex][colIndex] = 1 << initialBoard[rowIndex][colIndex]-1;
                }
            }
        }

        return binaryBoard;
    }

    @Override
    public int[][] getInitialBoard() {
        return binaryBoard;
    }
}
