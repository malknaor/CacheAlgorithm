package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUAlgoCacheImplTest implements IAlgoCacheTest{

    @Test
    public void getElement() {
        LRUAlgoCacheImpl<Integer, String> testObject = new LRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");

        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
        assertEquals(null, testObject.getElement(2));
    }

    @Test
    public void putElement() {
        LRUAlgoCacheImpl<Integer, String> testObject = new LRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        String s1 = testObject.putElement(40, "Forty");
        assertEquals("Ten", s1);
        String s2 = testObject.putElement(10, "Ten");
        assertEquals("Twenty", s2);
    }

    @Test
    public void removeElement() {
        LRUAlgoCacheImpl<Integer, String> testObject = new LRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        assertEquals(1, testObject.cache.size());
        testObject.putElement(20, "Twenty");
        assertEquals(2, testObject.cache.size());
        testObject.putElement(30, "Thirty");
        assertEquals(3, testObject.cache.size());
        testObject.removeElement(20);
        assertEquals(2, testObject.cache.size());
        testObject.removeElement(10);
        assertEquals(1, testObject.cache.size());
        testObject.removeElement(15);
        assertEquals(1, testObject.cache.size());
    }
}