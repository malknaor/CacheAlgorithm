package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomAlgoCacheImplTest implements IAlgoCacheTest {
    @Test
    public void getElement()  {
        IAlgoCache<Integer, String> testObject = new RandomAlgoCacheImpl<>(3);

        testObject.putElement(1, "One");
        testObject.putElement(2, "Two");
        testObject.putElement(3, "Three");

        assertEquals("One", testObject.getElement(1));
        assertEquals("Two", testObject.getElement(2));
        assertEquals("Three", testObject.getElement(3));
    }

    @Test
    public void putElement()  {
        IAlgoCache<Integer, String> testObject = new RandomAlgoCacheImpl<>(3);

        testObject.putElement(1, "one");
        testObject.putElement(2, "two");
        testObject.putElement(3, "three");

        assertEquals(null, testObject.putElement(1 , "one"));
        assertEquals(null, testObject.putElement(2 , "two"));
        assertEquals(null, testObject.putElement(3 , "three"));
    }

    @Test
    public void removeElement()  {
        IAlgoCache<Integer, String> testObject = new RandomAlgoCacheImpl<>(3);

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