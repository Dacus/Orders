package com.company;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
}
