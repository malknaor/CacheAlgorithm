package com.hit.algorithm;

import java.util.*;

public class SecondChanceAlgoImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    private LinkedList<Complex<K, V>> cacheQueue;

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
                complex.refBit = true;
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
                    complex.setValue(value);
                    complex.setRefBit(true);
                    break;
                }
            }
        } else if (cacheQueue.size() < capacity) {
            Complex<K, V> tempComplex = new Complex<>(key, value);
            cacheQueue.add(tempComplex);
        } else if (value != null){
            retVal = findPageToReplace(key, value);
            if (retVal == null){
                retVal = findPageToReplace(key, value);
            }
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
                cacheQueue.addFirst(tempComplex);
            } else {
                retVal = tempComplex.getValue();
                cacheQueue.add(new Complex<>(key, value));
            }
        }

        return retVal;
    }

    private class Complex<K, V> {
        K key;
        V value;
        boolean refBit;

        Complex(K key, V value) {
            this.key = key;
            this.value = value;
            refBit = false;
        }

        public void setValue(V value) {
            this.value = value;
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
