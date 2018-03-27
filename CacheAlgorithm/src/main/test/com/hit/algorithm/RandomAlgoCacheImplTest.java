package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomAlgoCacheImplTest {
    @Test
    public void getElement()  {
        RandomAlgoCacheImpl<Integer, String> testerRandGet = new RandomAlgoCacheImpl<>(3);

        testerRandGet.putElement(1, "One");
        testerRandGet.putElement(2, "Two");
        testerRandGet.putElement(3, "Three");

        assertEquals("One", testerRandGet.getElement(1));
        assertEquals("Two", testerRandGet.getElement(2));
        assertEquals("Three", testerRandGet.getElement(3));
    }

    @Test
    public void putElement()  {
        RandomAlgoCacheImpl<Integer, String> testerRandPut = new RandomAlgoCacheImpl<>(3);

        testerRandPut.putElement(1, "one");
        testerRandPut.putElement(2, "two");
        testerRandPut.putElement(3, "three");

        assertEquals("one", testerRandPut.putElement(1 , "one"));
        assertEquals("two", testerRandPut.putElement(2 , "two"));
        assertEquals("three", testerRandPut.putElement(3 , "three"));
    }

    @Test
    public void removeElement()  {
        RandomAlgoCacheImpl<Integer, String> testerRandRemove = new RandomAlgoCacheImpl<>(3);

        testerRandRemove.putElement(1, "one");
        assertEquals(1, testerRandRemove.cache.size());
        testerRandRemove.putElement(2, "two");
        assertEquals(2, testerRandRemove.cache.size());
        testerRandRemove.putElement(3, "three");
        assertEquals(3, testerRandRemove.cache.size());

        testerRandRemove.removeElement(1);
        assertEquals(2, testerRandRemove.cache.size());
        testerRandRemove.removeElement(2);
        assertEquals(1, testerRandRemove.cache.size());
        testerRandRemove.removeElement(3);
        assertEquals(0, testerRandRemove.cache.size());




    }

}