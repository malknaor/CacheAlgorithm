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
                    if (value != null) {
                        complex.setValue(value);
                    }
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

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String tempStr = "";

        for (Complex<K, V> complex: this.cacheQueue) {
            tempStr += complex.toString() + "\n";
        }

        return tempStr;
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

        /**
         * Returns a string representation of the object. In general, the
         * {@code toString} method returns a string that
         * "textually represents" this object. The result should
         * be a concise but informative representation that is easy for a
         * person to read.
         * It is recommended that all subclasses override this method.
         * <p>
         * The {@code toString} method for class {@code Object}
         * returns a string consisting of the name of the class of which the
         * object is an instance, the at-sign character `{@code @}', and
         * the unsigned hexadecimal representation of the hash code of the
         * object. In other words, this method returns a string equal to the
         * value of:
         * <blockquote>
         * <pre>
         * getClass().getName() + '@' + Integer.toHexString(hashCode())
         * </pre></blockquote>
         *
         * @return a string representation of the object.
         */
        @Override
        public String toString() {
            return this.key + ", " + this.value;
        }
    }
}
