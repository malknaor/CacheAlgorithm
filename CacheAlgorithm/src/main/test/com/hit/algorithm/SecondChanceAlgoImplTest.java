package com.hit.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecondChanceAlgoImplTest implements IAlgoCacheTest {

    @Test
    public void getElement() {
        SecondChanceAlgoImpl<Integer, String> testObject = new SecondChanceAlgoImpl<>(4);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");

        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
        assertEquals("Ten", testObject.getElement(10));
        assertEquals("Twenty", testObject.getElement(20));
        assertEquals("Thirty", testObject.getElement(30));
        assertEquals(null, testObject.getElement(40));
    }

    @Test
    public void putElement() {
        SecondChanceAlgoImpl<Integer, String> testObject = new SecondChanceAlgoImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.putElement(20, "Twenty");
        testObject.putElement(30, "Thirty");
        testObject.putElement(30, "Thirty");

        assertEquals("Ten", testObject.putElement(40, "Forty"));
        assertEquals("Twenty", testObject.putElement(10, "Ten"));
        assertEquals("Thirty", testObject.putElement(20, "Twenty"));

        testObject.putElement(40, "Forty");
        testObject.putElement(20, "Twenty");
    }

    @Test
    public void removeElement() {
        SecondChanceAlgoImpl<Integer, String> testObject = new SecondChanceAlgoImpl<>(3);

        testObject.putElement(10, "Ten");
        testObject.putElement(20, "Twenty");
        testObject.removeElement(20);
        assertEquals(1, testObject.getCurrentCapacity());
        testObject.removeElement(10);
        assertEquals(0, testObject.getCurrentCapacity());
    }
}