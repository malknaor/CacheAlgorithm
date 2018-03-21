package com.hit.algorithm;

import java.util.Queue;

public class SecondChanceAlgoImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {


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

    class Complex<K, V> {
        K key;
        V value;
        boolean validBit;
        boolean referenceBit;

        public Complex(K key, V value) {
            this.key = key;
            this.value = value;
            validBit = true;
            referenceBit = false;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean isValidBit() {
            return validBit;
        }

        public void setValidBit(boolean validBit) {
            this.validBit = validBit;
        }

        public boolean isReferenceBit() {
            return referenceBit;
        }

        public void setReferenceBit(boolean referenceBit) {
            this.referenceBit = referenceBit;
        }
    }
}
