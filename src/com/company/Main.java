package com.company;

import java.util.HashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LastTouchMapImpl<Integer, String> a = new LastTouchMapImpl();
        a.put(1, "m1");
        a.put(2, "m2");
        a.put(3, "m3");
        a.put(4, "m4");
        a.put(5, "m5");
        a.put(6, "m6");
        HashMap c = new HashMap();
        c.put(1, "m1");
        c.put(2, "m2");
        c.put(3, "m3");
        c.put(4, "m4");
        c.put(5, "m5");
        c.put(6, "m6");
        Set e = c.entrySet();
        Set f = c.keySet();
        Set b = a.entrySet();
        System.out.println(a);
        b.remove(b.iterator().next());
        System.out.println(e);


    }


}
