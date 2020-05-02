package com.hadi.ex1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * An object that contains all the information about a website that we need in this exercise
 */
public class Url implements Comparable<Url> {

    /** Contains content size */
    private int contentLength;

    /** Website url */
    private String url;

    /**
     * Creates a Url object that contains information we need about the website that is passed
     * @param url website url
     * @throws Exception could be: Invalid url, invalid website
     */
    public Url(final String url) throws Exception {
        this.url = url;
        final URL urlObject = new URL(this.url);

        final InputStream in = new BufferedInputStream(urlObject.openStream());

        contentLength = getLength(in);

        try {
            in.close();
        } catch (IOException e) { }

    }


    /**
     * Counts the length of the stream. if an error occurs while reading it will return 0
     * @param in stream object
     * @return length of stream
     */
    private static int getLength(final InputStream in) {
        int c = 0;

        try {
            while (in.read() != -1)
                c++;
        } catch (IOException e) { }

        return c;
    }


    /**
     * Get website url (web page)
     * @return website url
     * {@link #url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get website content length (size of web page)
     * @return webpage content size
     * {@link #contentLength}
     */
    public int getContentLength() {
        return contentLength;
    }

    /**
     * Website URL and content size
     * @return website url and size in string
     */
    @Override
    public String toString() {
        return getUrl() + " " + getContentLength();
    }

    /**
     * Compares between two Url objects by their size
     * @param o object to compare with
     * @return if this object is bigger\equals\less (1\0\-1)
     */
    @Override
    public int compareTo(Url o) {
        return Integer.compare(this.getContentLength(), o.getContentLength());
    }
}
