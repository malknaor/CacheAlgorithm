package com.hit.algorithm;

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
            cache.get(key).count = 0;

            for (Complex complex : cache.values()) {
                complex.count++;
            }
        }

        return val;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;

        if (cache.containsKey(key)) {
            cache.get(key).setValue(value);
            cache.get(key).setCount(0);
        } else if (cache.size() < capacity) {
            cache.put(key, new Complex<>(value));
        } else if (value != null){
            K keyToRemove = null;
            Object[] array = cache.values().toArray();
            Integer mostRecent = ((Complex<V>) array[0]).getCount();
            retVal = ((Complex<V>) array[0]).getValue();

            for (K currentKey : cache.keySet()) {
                if (mostRecent > cache.get(currentKey).getCount()) {
                    retVal = cache.get(currentKey).getValue();
                    mostRecent = cache.get(currentKey).getCount();
                    keyToRemove = currentKey;
                }
            }

            cache.put(key, new Complex<>(value));
            cache.remove(keyToRemove);
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

    private class Complex<V> {
        private V value;
        private Integer count;

        public Complex(V value) {
            this.value = value;
            count = 0;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}