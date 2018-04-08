package com.hit.algorithm;

import java.util.HashMap;
import java.util.Map;

import java.util.Random;

public class RandomAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    private Map<K, V> cache;

    public RandomAlgoCacheImpl(int capacity) {
        super(capacity);
        cache = new HashMap<>();
    }

    @Override
    public int getCurrentCapacity() {
        return cache.size();
    }

    @Override
    public V getElement(K key) {
        V Val = null;

        if (cache.containsKey(key)) {
            Val = cache.get(key);
        }

        return Val;

    }

    @Override
    public V putElement(K key, V value) {
        V retVal;

        if (cache.containsKey(key)) {
            retVal = cache.get(key);
        } else if (cache.size() < capacity) {
            cache.put(key, value);
            retVal = value;
        } else {
            Random random = new Random();
            Integer randomValue = random.nextInt(capacity);
            Object[] array = cache.values().toArray();
            retVal = (V)(array[randomValue]);
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
