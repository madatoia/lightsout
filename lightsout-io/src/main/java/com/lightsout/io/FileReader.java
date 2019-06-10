package com.lightsout.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that loads resources and reads the requested files
 */
public class FileReader {

    /**
     * Reads the requested file line by line
     *
     * @param fileName the file name
     * @return list of all the lines of the file
     */
    public List<String> getFile(final String fileName) throws IOException {

        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(fileName).getFile());

        return Files.lines(file.toPath()).collect(Collectors.toList());

    }
}
