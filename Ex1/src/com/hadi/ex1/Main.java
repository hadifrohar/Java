package com.hadi.ex1;

/**
 * This program reads urls from a file (input file) (each line is a url), for every valid url (that presents a
 * valid website) it gets the size of the content of the page and writes the url and the size to the output file
 */
public class Main {

    /**
     * Creates the reader object to read from the input file, creates a writer object to write to the output file,
     * creates object creator that creates the needed object (for our needs, it creates a Url object) and creates
     * the processor object that processes that handles all the logic
     * @param args args[0] = input file, args[1] = output file
     */
    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("wrong usage");
            System.exit(0);
        }

        final UrlsReader reader = new UrlsReader(args[0]);
        final UrlsWriter writer = new UrlsWriter(args[1]);

        final ObjectCreator urlCreator = new UrlCreator();

        final Processor urlProcessor = new Processor(reader, writer, urlCreator);
        urlProcessor.process();
    }
}
