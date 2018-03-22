package com.hit.algorithm;

import java.util.*;

public class SecondChanceAlgoImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    /*private */Queue<Complex<K, V>> cacheQueue;

    public SecondChanceAlgoImpl(int capacity) {
        super(capacity);
        cacheQueue = new LinkedList<>();
    }

    @Override
    public V getElement(K key) {
        V retVal = null;

        for (Complex<K, V> complex : cacheQueue) {
            if (complex.getKey() == key) {
                retVal = complex.getValue();
                break;
            }
        }

        return retVal;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;
        boolean checkIfExist = ifKeyExist(key);

        if (checkIfExist) {
            for (Complex<K, V> complex: cacheQueue) {
                if (complex.getKey() == key){
                    complex.setRefBit(true);
                    retVal = complex.getValue();
                    break;
                }
            }
        } else if (cacheQueue.size() < capacity) {
            Complex<K, V> tempComplex = new Complex<>(key, value);
            retVal = tempComplex.getValue();
            cacheQueue.add(tempComplex);
        } else {
            retVal = findPageToReplace(key, value);
        }

        return retVal;
    }

    @Override
    public void removeElement(K key) {
        Complex<K, V> toRemove = null;

        for (Complex<K, V> complex : cacheQueue){
            if (complex.key == key) {
                toRemove = complex;
                cacheQueue.remove(toRemove);
                break;
            }
        }
    }

    private boolean ifKeyExist(K key) {
        boolean exist = false;

        for (Complex<K, V> complex : cacheQueue){
            if (complex.getKey() == key)
                exist = true;
        }

        return exist;
    }

    private V findPageToReplace(K key, V value) {
        V retVal = null;
        Complex<K, V> tempComplex = cacheQueue.poll();

        if (tempComplex != null) {
            if (tempComplex.isRefBit()) {
                tempComplex.setRefBit(false);
                retVal = findPageToReplace(key, value);
                ((LinkedList<Complex<K, V>>)cacheQueue).addFirst(tempComplex);
            } else {
                retVal = tempComplex.getValue();
                ((LinkedList<Complex<K, V>>)cacheQueue).addFirst(tempComplex);
            }
        }

        return retVal;
    }

    private class Complex<K, V> {
        K key;
        V value;
        boolean refBit;

        public Complex(K key, V value) {
            this.key = key;
            this.value = value;
            refBit = false;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean isRefBit() {
            return refBit;
        }

        public void setRefBit(boolean refBit) {
            this.refBit = refBit;
        }
    }
}
