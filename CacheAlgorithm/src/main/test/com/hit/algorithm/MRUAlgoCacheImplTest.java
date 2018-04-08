package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class MRUAlgoCacheImplTest implements IAlgoCacheTest{

    @Test
    public void getElement() {
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

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
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        assertEquals("Thirty", testObject.putElement(40, "Forty"));
        assertEquals("Forty", testObject.putElement(30, "Thirty"));
    }

    @Test
    public void removeElement() {
        MRUAlgoCacheImpl<Integer, String> testObject = new MRUAlgoCacheImpl<>(3);

        assertEquals(0, testObject.getCurrentCapacity());
        testObject.putElement(10, "Ten");
        assertEquals(1, testObject.getCurrentCapacity());
        testObject.putElement(20, "Twenty");
        assertEquals(2, testObject.getCurrentCapacity());
        testObject.putElement(30, "Thirty");
        assertEquals(3, testObject.getCurrentCapacity());
        testObject.removeElement(20);
        assertEquals(2, testObject.getCurrentCapacity());
    }
}