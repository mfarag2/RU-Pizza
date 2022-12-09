package com.example.project_5;

/**
 * Customizable interface that identifies different methods
 * @param <E> Generic type
 * @author Mary Farag
 */
public interface Customizable<E> {
    /**
     * Add the object to the list
     *
     * @param obj object to be added
     * @return true if it is added correctly, false otherwise
     */
    boolean add(Object obj);

    /**
     * Remove the object from the list
     *
     * @param obj object to be removed
     * @return true if it is removed correctly, false otherwise
     */
    boolean remove(Object obj);
}

