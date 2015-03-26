package com.company;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void testTest1() throws Exception {
        Main main = new Main();
        String []actual = main.test();
        assertThat(Arrays.asList(actual),contains("ceva","altceva"));

    }
}