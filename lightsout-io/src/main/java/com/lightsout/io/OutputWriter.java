package com.lightsout.io;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Writes the result as pairs of coordinates where the upper left corner is the origin. The pairs are separated between them by space and the coordinates inside a pair are separated by ","
 */
public class OutputWriter {

    /**
     * Writes in the console all the pairs in the result using writePosition to output each of the pairs
     *
     * @param result the result to be printed
     */
    public void writeResult(int[][] result) {
        System.out.println(Arrays.stream(result)
                .map(this::writePosition)
                .collect(Collectors.joining(" ")));
    }

    /**
     * Writes the received position to the console, separating the values with ","
     *
     * @param position the position to be written
     */
    private String writePosition(int[] position) {
        return Arrays.stream(position)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
