package com.hit.algorithm;

public abstract class AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    protected int capacity;

    public AbstractAlgoCache(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract int getCurrentCapacity();
}