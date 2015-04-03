package com.company;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
        assertEquals(list.size(), 2);
    }

    @Test
    public void testPut() throws Exception {

    }

    @Test
    public void testGetLast() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(10,"Alex");
        list.put(2,"Andi");
        list.put(3,"Cosmin");
        list.put(2,"Gelu");
        List<String> l = list.getLast(3);
        assertThat(l, contains("Gelu", "Cosmin", "Alex"));
    }

    @Test
    public void testFindEntry() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(10,"Alex");
        list.put(2,"Andi");
        list.put(3,"Cosmin");
        list.put(2, "Gelu");
 //       assertThat(((LastTouchMapImpl.Entry)list.findEntry(2)).toString(), contains(""));
    }


}