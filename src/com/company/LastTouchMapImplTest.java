package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class LastTouchMapImplTest {

    @Test
    public void testSizeZero() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        assertEquals(list.size(), 0);
    }
    @Test
    public void testSizeTwo() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(1,"ceva");
        list.put(2,"altceva");
        assertEquals(list.size(),2);
    }

    @Test
    public void testPut() throws Exception {

    }

    @Test
    public void testGetLast() throws Exception {

    }

    @Test
    public void testFindEntry() throws Exception {

    }
}