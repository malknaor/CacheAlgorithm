package com.hit.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    private Map<K, Complex<V>> cache;

    public MRUAlgoCacheImpl(int capacity) {
        super(capacity);
        cache = new HashMap<K, Complex<V>>();
    }

    @Override
    public V getElement(K key) {
        V val = null;

        if (cache.containsKey(key)) {
            val = cache.get(key).value;
        }

        return val;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;

        if (cache.containsKey(key)) {
            cache.get(key).count = 0;
            retVal = cache.get(key).getValue();
        }
        else if (cache.size() < capacity) {
            cache.put(key, new Complex<>(value));
            retVal = value;
        }
        else {
            Object[] array = cache.values().toArray();
            Integer mostRecent = ((Complex<V>)array[0]).count;

            for (Complex complex : cache.values()) {
                if (mostRecent < complex.count)
                    retVal = (V) complex.getValue();
                    mostRecent = complex.count;
            }
        }

        for (Complex complex : cache.values()) {
            complex.count++;
        }

        return retVal;
    }

    @Override
    public void removeElement(K key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    class Complex<V> {
        private V value;
        private Integer count;

        public Complex(V value) {
            this.value = value;
            count = 0;
        }

        public V getValue() {
            return value;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}