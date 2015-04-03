package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MyLastTouchMapTest {
    private LastTouchMapImpl<Integer, String> map;

    @Before
    public void setup() {
        map = new LastTouchMapImpl<>();
    }

    @Test
    public void testPutOneElement() {
        map.put(1, "my order");
        List<String> l = map.getLast(2);

        assertThat(map.size(), equalTo(1));
        assertThat(l, contains("my order"));
    }

    @Test
    public void testUpDateOneElement() {
        map.put(1, "my order1");
        map.put(1, "my order2");
        List<String> l = map.getLast(2);

        assertThat(map.size(), equalTo(1));
        assertThat(l, contains("my order2"));
    }

    @Test
    public void testPutTwoElement() {
        map.put(1, "my order1");
        map.put(2, "my order2");
        map.put(1, "my order1 changed");
        List<String> l = map.getLast(2);

        assertThat(map.size(), equalTo(2));
        assertThat(l, contains("my order2", "my order1 changed"));
    }

    @Test
    public void testGetLastLessThanListSize() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");

        List<String> l = map.getLast(3);
        assertThat(l, contains("m3", "m4", "m5"));
    }

    @Test
    public void testGetLastLessThanHalfListSize() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");
        map.put(6, "m6");
        map.put(7, "m7");

        List<String> l = map.getLast(3);
        assertThat(l, contains("m5", "m6", "m7"));
    }

    @Test
    public void testGetLastLessThanHalfListSizeOne() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");
        map.put(6, "m6");
        map.put(7, "m7");

        List<String> l = map.getLast(1);
        assertThat(l, contains("m7"));
    }

    @Test
    public void testGetLastLessThanHalfListSizeZero() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");
        map.put(6, "m6");
        map.put(7, "m7");

        List<String> l = map.getLast(0);
        assertThat(l, empty());
    }

    @Test
    public void testGetEqualToListSize() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");
        map.put(4, "m4");
        map.put(3, "m3");

        List<String> l = map.getLast(5);
        assertThat(l, contains("m1", "m2", "m5", "m4", "m3"));
    }

    @Test
    public void testGetGreaterThanListSize() {
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");
        map.put(4, "m4");
        map.put(5, "m5");
        map.put(4, "m4");
        map.put(3, "m3");

        List<String> l = map.getLast(15);
        assertThat(l, contains("m1", "m2", "m5", "m4", "m3"));
    }

    @Test
    public void testIsEmptyTrue() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(10, "Alex");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testContainsKeyTrue() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(1, "m1");
        list.put(2, "m2");
        list.put(3, "m3");
        list.put(4, "m4");
        list.put(5, "m5");
        list.put(4, "m4");
        list.put(3, "m3");
        boolean a = list.containsKey(5);
        assertThat(a, is(true));
    }

    @Test
    public void testContainsKeyFalse() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(1, "m1");
        list.put(2, "m2");
        list.put(3, "m3");
        list.put(4, "m4");
        list.put(5, "m5");
        list.put(4, "m4");
        list.put(3, "m3");
        boolean a = list.containsKey(10);
        assertThat(a, is(false));
    }

    @Test
    public void testKeySet() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(1, "m1");
        list.put(2, "m2");
        list.put(3, "m3");
        list.put(4, "m4");
        list.put(5, "m5");
        list.put(4, "m4");
        list.put(3, "m3");
        Set<Integer> keys = list.keySet();
        assertThat(keys, contains(3, 4, 5, 2, 1));
    }

    @Test
    public void testClear() throws Exception {
        LastTouchMapImpl<Integer, String> list = new LastTouchMapImpl<>();
        list.put(1, "m1");
        list.put(2, "m2");
        list.put(3, "m3");
        list.put(4, "m4");
        list.put(5, "m5");
        list.put(4, "m4");
        list.put(3, "m3");
        list.clear();
        assertTrue(list.isEmpty());
    }
}
