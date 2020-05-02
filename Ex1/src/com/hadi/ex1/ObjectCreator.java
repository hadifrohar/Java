package com.hadi.ex1;

/**
 * An interface to enable generic object creation
 * @param <T> Object type
 */
public interface ObjectCreator<T> {

    /**
     * Creates generic object from a string. (For our use: creates an Url object)
     * @param str string
     * @return created object
     */
    public T create(final String str);
}
