package com.hadi.ex1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * A buffered writer object with the option to write a generic list to file
 */
public class Writer {
    /** * Input file pathname */
    private final String pathname;

    /**
     * Creates writer class
     * @param pathname contains output file path
     */
    public Writer(final String pathname) {
        this.pathname = pathname;
    }

    /**
     * Writes to file all the objects in the list, each object will be written in a new line
     * @param list contains objects to be written
     * @param <T> type of the object (for generic use)
     */
    public <T> void write(final List<T> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathname))) {
            for (Object object : list) {
                writer.write(object.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            //System.err.println("bad input");
        }
    }
}
