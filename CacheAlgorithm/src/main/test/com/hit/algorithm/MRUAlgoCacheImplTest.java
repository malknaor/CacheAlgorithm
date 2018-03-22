package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class MRUAlgoCacheImplTest {

    @Test
    public void getElement() {
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");

        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
    }

    @Test
    public void putElement() {
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        assertEquals("Thirty", testObject.putElement(40, "Forty"));
    }

    @Test
    public void removeElement() {
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");
        testObject.removeElement(20);

        assertEquals(2, testObject.cache.size());
    }
}