package com.lightsout.domain.binaryimpl;

import com.lightsout.domain.integerimpl.BoardOperations;
import com.lightsout.domain.integerimpl.Puzzle;

/**
 * Class that knows how how to modify the binary board
 */
public class BinaryBoardOperations extends BoardOperations {

    /**
     * Applies a piece on the board by shifting to the left the cell value with one bit. In case the max depth is hit,
     * the mask resets the value to 0.
     *
     * @param puzzle     the puzzle containing the mask and the pieces
     * @param board      the bord on which to apply the piece
     * @param pieceIndex the piece to apply
     * @param posX       x coord of the position where the piece should be applied
     * @param posY       y coord of the position where the piece should be applied
     * @return the resulting board
     */
    @Override
    public int[][] applyPieceOnBoard(Puzzle puzzle, int[][] board, int pieceIndex, int posX, int posY) {

        final int[][] newBoard = copyBoard(board);
        final int[][] piece = puzzle.getPieces()[pieceIndex];
        final int mask = ~(Integer.MAX_VALUE << puzzle.getDepth() - 1);

        for (int i = posX; i < piece.length + posX; i++) {
            for (int j = posY; j < piece[0].length + posY; j++) {
                if (piece[i - posX][j - posY] == 1 && newBoard[i][j] == 0) {
                    newBoard[i][j] = 1;
                } else {
                    newBoard[i][j] = (newBoard[i][j] << piece[i - posX][j - posY]) & mask;
                }
            }
        }
        return newBoard;
    }

    /**
     * Since instead of adding to the board, we are shifting bits, in order to determine the complement,
     * we have to shift them back until we reach 0. The complement will be depth minus the number of shifts.
     *
     * @param puzzle puzzle containing the depth
     * @param value  the number for which we have to determine the complement
     * @return the complement
     */
    @Override
    public int computeCellComplement(Puzzle puzzle, int value) {
        int valueCopy = value;
        int shifts = 0;

        while (valueCopy != 0) {
            valueCopy = valueCopy >> 1;
            shifts++;
        }
        return puzzle.getDepth() - shifts;
    }

}
