package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class MRUAlgoCacheImplTest implements IAlgoCacheTest{

    @Test
    public void getElement() {
        IAlgoCache<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
        assertEquals(null, testObject.getElement(2));
        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
        assertEquals("Thirty", testObject.getElement(30));
    }

    @Test
    public void putElement() {
        IAlgoCache<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        assertEquals("Thirty", testObject.putElement(40, "Forty"));
        assertEquals("Forty", testObject.putElement(30, "Thirty"));
    }

    @Test
    public void removeElement() {
        IAlgoCache<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        testObject.removeElement(20);
        assertEquals(null, testObject.getElement(20));
        testObject.removeElement(10);
        assertEquals(null, testObject.getElement(10));
        testObject.removeElement(30);
        assertEquals(null, testObject.getElement(30));
    }
}