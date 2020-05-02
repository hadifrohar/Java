package com.hadi.ex1;

import java.util.Collections;
import java.util.List;

/**
 * This is the main logic of the program
 * It takes a reader object that reads from a file (reads a list) and a writer object that writes to a file (the
 * list) after processing
 */
public class Processor {
    /** UrlsReader object */
    final private UrlsReader reader;

    /** UrlsWriter object */
    final private UrlsWriter writer;

    /** Object creator */
    final private ObjectCreator creator;

    /**
     * Creates a processor object to process a list from the reader and outputs it to the writer
     * @param reader reads a file
     * @param writer writes to a file
     * @param creator creates an object
     */
    public Processor(final UrlsReader reader, final UrlsWriter writer, final ObjectCreator creator){
        this.reader = reader;
        this.writer = writer;
        this.creator = creator;
    }

    /**
     * Calls for  the reader to get the list, sorts it and calls for the writer to write it (to the output file)
     */
    public void process(){
        final List<Comparable> list = reader.readList(creator);
        Collections.sort(list);
        writer.write(list);
    }
}
