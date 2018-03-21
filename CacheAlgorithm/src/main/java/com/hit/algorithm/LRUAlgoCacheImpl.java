package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    private Map<K, Complex<V>> cache;

    public LRUAlgoCacheImpl(int capacity){
        super(capacity);
        cache = new HashMap<K, Complex<V>>();
    }

    @Override
    public V getElement(K key) {
        return cache.get(key).value;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal;

        if (cache.containsKey(key)) {
            cache.get(key).count = 0;
            retVal = cache.get(key).value;
        } else if (cache.size() < capacity) {
            cache.put(key, new Complex<>(value));
            retVal = value;
        } else {
            

        }

    }

    @Override
    public void removeElement(K key) {

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
