package com.hit.algorithm;

import java.util.HashMap;
import java.util.Map;

public class MRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V>{

    private Map<K, V> cache;

    public MRUAlgoCacheImpl(int capacity) {
        super(capacity);
        cache = new HashMap<K, V>();
    }

    @Override
    public V getElement(K key) {
        return cache.get(key);
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;

        if (cache.size() < capacity && !cache.containsKey(key)) {
            retVal = cache.put(key, value);
        } else {
            //replace the page
        }

        return retVal;
    }

    @Override
    public void removeElement(K key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }
}
