package com.lightsout.solver;

/**
 * Wrapper class that contains the positions on which the pieces have to be applied and true if we reach a solution or false otherwise
 */
class Result {

    private int[][] positionsToApply;
    private boolean found;

    /**
     * Constructor that creates a empty result, based on the number of pieces to be applied
     */
    Result(int numberOfPiecesToBeApplied) {
        positionsToApply = new int[numberOfPiecesToBeApplied][2];
    }

    boolean isFound() {
        return found;
    }

    void setFound(boolean found) {
        this.found = found;
    }

    /**
     * Adds coordinates on which the piece on position index has to be applied
     */
    void addToResult(int x, int y, int index) {
        positionsToApply[index][0] = x;
        positionsToApply[index][1] = y;
    }

    int[][] getPositionsToApply() {
        return positionsToApply;
    }
}

