package com.hit.algorithm;

/**
 *
 * @param <K>
 * @param <V>
 */
public interface IAlgoCache<K, V> {
    /**
     * Returns the value to which the specified key is mapped, or null if this cache contains no mapping for the key.
     * In addition performs the relevant cache algorithm.
     * @param key - with which the specified value is to be associated
     * @return - the value to which the specified key is mapped, or null if this cache contains no mapping for the key
     */
    V getElement(K key);

    /**
     * Associates the specified value with the specified key in this cache according to the current algorithm
     * @param key - with which the specified value is to be associated
     * @param value - to be associated with the specified key
     * @return return the value of the element which need to be replaced
     */
    V putElement(K key, V value);

    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key - whose mapping is to be removed from the cache according to the current algorithm
     */
    void removeElement(K key);
}
