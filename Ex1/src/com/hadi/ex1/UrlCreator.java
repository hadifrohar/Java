package com.hadi.ex1;


/**
 * An class that contains a method to create a Url object from string (only valid url)
 */
public class UrlCreator implements ObjectCreator<Url> {

    /**
     * Creates url object from a string
     * @param str contains website (webpage) url
     * @return Url object if the url is valid (protocol:host....), otherwise null
     */
    @Override
    public Url create(final String str) {

        Url url = null;

        try {
            url = new Url(str);
        } catch (Exception e) {
            //System.err.println(e.getMessage());
        }

        return url;
    }
}
