package com.hadi.ex1;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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

        final URLConnection connection = connect(urlObject);

        // Update content length, if an error occurs it will stay zero
        final int length = connection.getContentLength();
        contentLength = length == -1 ? 0 : length;

    }


    /*public Url(final Url urlObject) {
        this.url = urlObject.getUrl();
        this.contentLength = urlObject.getContentLength();
        this.isValid = urlObject.isValid();
    }*/

    /**
     * Opens a connection and connects to the website, if connection fails an IOException will be thrown
     * @param url URL object to connect
     * @return URLConnection object for the url
     * @throws IOException if connection fails
     */
    private static URLConnection connect(final URL url) throws Exception {
        final URLConnection connection = url.openConnection();
        connection.connect();
        return connection;

    }
    /**
     * Get website url (web page)
     * @return website url
     * {@link #url}
     */
    protected String getUrl() {
        return url;
    }

    /**
     * Get website content length (size of web page)
     * @return webpage content size
     * {@link #contentLength}
     */
    protected int getContentLength() {
        return contentLength;
    }

    /**
     * Covnerts members to string.
     * URL size
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
