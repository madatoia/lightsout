package com.lightsout.domain;

import com.lightsout.domain.binaryimpl.BinaryBoardOperations;
import com.lightsout.domain.integerimpl.BoardOperations;

public class BoardOperationsFactory {

    /**
     * Creates a {@link BoardOperations} service based on the provided  {@link PuzzleType}
     */
    public BoardOperations createBoardOperations(PuzzleType puzzleType) {
        return puzzleType == PuzzleType.BINARY_IMPLEMENTATION ? new BinaryBoardOperations()
                : new BoardOperations();
    }
}
