package com.hit.algorithm;

import java.util.*;

public class SecondChanceAlgoImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    Queue<Complex<K, V>> cacheQueue;

    public SecondChanceAlgoImpl(int capacity) {
        super(capacity);
        cacheQueue = new LinkedList<>();
    }

    @Override
    public V getElement(K key) {
        V retVal = null;

        for (Complex<K, V> complex : cacheQueue) {
            if (complex.key == key) {
                retVal = complex.value;
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
                if (complex.key == key){
                    complex.setRefBit(true);
                    retVal = complex.value;
                    break;
                }
            }
        } else if (cacheQueue.size() < capacity) {
            Complex<K, V> tempComplex = new Complex<>(key, value);
            retVal = tempComplex.value;
            cacheQueue.add(tempComplex);
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
        boolean exist = true;

        for (Complex<K, V> complex : cacheQueue){
            if (complex.key == key)
                exist = false;
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
                cacheQueue.add(tempComplex);
            } else {
                retVal = tempComplex.value;
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
