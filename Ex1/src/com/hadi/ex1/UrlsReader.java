package com.hadi.ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader class that reads all file content and creates objects from it (based on our needs)
 */
public class UrlsReader {

    /** Input file pathname */
    private final String pathname;

    /**
     * Creates reader class
     * @param pathname input file pathname
     */
    public UrlsReader(final String pathname) {
        this.pathname = pathname;

    }

    /**
     * Reads a file content and creates a list of objects from it
     * @param objectCreator this object creates an object from every line in the file
     * @param <T> object type to be created (for generic use)
     * @return list of created objects
     */
    public <T> List<T> readList(final ObjectCreator<T> objectCreator) {
        final List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathname))) {
            String line;

            //for every line create an object using objectCreator, and if it's not null add it to the list
            while ((line = reader.readLine()) != null) {
                final T object = objectCreator.create(line);
                if (object != null)
                    list.add(object);
            }
        } catch (IOException e) {
            System.err.println("bad input");
            System.exit(0);
        }

        return list;

    }

}
