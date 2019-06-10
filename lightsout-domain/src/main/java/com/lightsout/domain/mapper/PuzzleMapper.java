package com.lightsout.domain.mapper;

import com.lightsout.domain.integerimpl.Puzzle;

import java.util.List;

/**
 * Parses the content of the 3 strings received in order to extract the problem's details.
 * String 1: “depth” of the puzzle. This will always be 2, 3 or 4.
 * String 2: initial board state. Each row is separated by a comma. Each digit represents the initial value for the cell.
 * String 3: individual pieces. Each piece is separated by a space. Each row within a piece is separated by a comma. ‘.’ means no increment and ‘X’ means increment by 1.
 */
public class PuzzleMapper {

    /**
     * Creates a {@link Puzzle} by parsing the inputted lines
     *
     * @param fileContent the file content provided line by line
     * @return the resulting puzzle
     */
    public Puzzle createPuzzle(List<String> fileContent) {

        int depth = readDepth(fileContent.get(0));
        int[][] board = readMatrix(fileContent.get(1));
        int[][][] pieces = readPieces(fileContent.get(2));

        return new Puzzle(board, pieces, depth);
    }

    /**
     * Reads the pieces by first convert them to the board format (X becomes 1 and . becomes 0) and then reads them as a board, one by one
     *
     * @param line the line containing pieces information
     * @return array of the resulting pieces, in binary format
     */
    private int[][][] readPieces(String line) {
        final String binaryPieces = convertToBoardFormat(line);

        final String[] piecesInStringFormat = binaryPieces.split(" ");
        final int noOfPieces = piecesInStringFormat.length;
        final int[][][] pieces = new int[noOfPieces][][];

        for (int piecesIndex = 0; piecesIndex < noOfPieces; piecesIndex++) {
            pieces[piecesIndex] = readMatrix(piecesInStringFormat[piecesIndex]);
        }

        return pieces;
    }

    /**
     * Converts a piece to board format by replacing X with 1 and . with 0
     *
     * @param line the line containing all the pieces
     * @return the updated line
     */
    private String convertToBoardFormat(final String line) {
        final String xTo1 = line.replaceAll("X", "1");
        return xTo1.replaceAll("\\.", "0");
    }

    private int[][] readMatrix(final String line) {
        final String[] matrixLines = line.split(",");
        final int matrixHeight = matrixLines.length;
        final int matrixWidth = matrixLines[0].length();
        final int[][] matrix = new int[matrixHeight][matrixWidth];

        for (int rowIndex = 0; rowIndex < matrixHeight; rowIndex++) {
            for (int lineIndex = 0; lineIndex < matrixWidth; lineIndex++) {
                matrix[rowIndex][lineIndex] = matrixLines[rowIndex].charAt(lineIndex) - '0';
            }
        }
        return matrix;
    }

    private int readDepth(final String line) {
        return Integer.parseInt(line);
    }
}
