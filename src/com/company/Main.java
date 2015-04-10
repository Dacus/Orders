package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        HashMap a = new HashMap();
        a.put(1, "m1");
        a.put(2, "m2");
        a.put(3, "m3");
        a.put(4, "m4");
        a.put(5, "m5");
        Set<Integer> b = a.keySet();
        System.out.println(b);

    }


}
