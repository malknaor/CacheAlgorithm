package com.hit.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

    private Map<K, Complex<V>> cache;

    public LRUAlgoCacheImpl(int capacity){
        super(capacity);
        cache = new HashMap<K, Complex<V>>();
    }

    @Override
    public V getElement(K key)
    {
        V retVal = null;

        if (cache.containsKey(key)) {
            retVal = cache.get(key).value;
            cache.get(key).count = 0;

            for (Complex complex : cache.values()) {
                complex.count++;
            }
        }

        return retVal;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;

        if (cache.containsKey(key)) {
            if (value != null) {
                cache.get(key).setValue(value);
            }
            cache.get(key).count = 0;
        } else if (cache.size() < capacity) {
            cache.put(key, new Complex<>(value));
        } else if (value != null){
            K keyToRemove = null;
            Object[] array = cache.values().toArray();
            Integer mostRecent = ((Complex<V>) array[0]).count;
            retVal = ((Complex<V>) array[0]).getValue();

            for (K currentKey : cache.keySet()) {
                if (mostRecent < cache.get(currentKey).count) {
                    retVal = cache.get(currentKey).getValue();
                    mostRecent = cache.get(currentKey).count;
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
        if(cache.containsKey(key))
        {
            cache.remove(key);
        }
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

        for (K id: this.cache.keySet()) {
            tempStr += id + ", " + this.cache.get(id) + "\n";
        }

        return tempStr;
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
