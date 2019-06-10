package com.lightsout.domain;

import com.lightsout.domain.binaryimpl.BinaryPuzzle;
import com.lightsout.domain.integerimpl.Puzzle;
import com.lightsout.domain.mapper.PuzzleMapper;

import java.util.List;

/**
 * Class that creates a Puzzle based on the requested type
 */
public class PuzzleFactory {

    private final PuzzleMapper puzzleMapper = new PuzzleMapper();

    /**
     * Creates a puzzle of the provided type based on the serialised input
     *
     * @param serialisedPuzzle the serialised version of the puzzle
     * @param puzzleType       the type of puzzle we want to keep in memory
     * @return the resulting puzzle
     */
    public Puzzle createPuzzle(List<String> serialisedPuzzle, PuzzleType puzzleType) {
        Puzzle puzzle;

        if (puzzleType == PuzzleType.INTEGER_IMPLEMENTATION) {
            puzzle = puzzleMapper.createPuzzle(serialisedPuzzle);
        } else {
            puzzle = new BinaryPuzzle(puzzleMapper.createPuzzle(serialisedPuzzle));
        }
        puzzle.initialize();
        return puzzle;
    }
}
