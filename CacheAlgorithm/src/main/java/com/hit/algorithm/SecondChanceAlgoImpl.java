package com.hit.algorithm;

public class SecondChanceAlgoImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V>{

    public SecondChanceAlgoImpl(int capacity) {
        super(capacity);
    }

    @Override
    public V getElement(K key) {
        return null;
    }

    @Override
    public V putElement(K key, V value) {
        return null;
    }

    @Override
    public void removeElement(K key) {

    }
}
