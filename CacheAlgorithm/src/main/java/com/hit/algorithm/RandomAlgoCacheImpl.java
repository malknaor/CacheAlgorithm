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
    public V getElement(K key) {
        V Val = null;

        if (cache.containsKey(key)) {
            Val = cache.get(key);
        }

        return Val;
    }

    @Override
    public V putElement(K key, V value) {
        V retVal = null;

        if (cache.containsKey(key)) {
            if (value != null) {
                cache.put(key, value);
            }
            retVal = null;
        } else if (cache.size() < capacity) {
            cache.put(key, value);
        } else if (value != null){
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
}
