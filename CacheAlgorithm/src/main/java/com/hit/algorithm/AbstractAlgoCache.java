package com.hit.algorithm;

/**
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    protected int capacity;

    /**
     * constructor
     * @param capacity - set the desired capacity.
     */
    public AbstractAlgoCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * getter for the capacity member
     * @return - the capacity of the object
     */
    public int getCapacity() {
        return capacity;
    }
}