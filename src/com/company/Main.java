package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        LastTouchMapImpl<Integer, String> a = new LastTouchMapImpl<>();
        a.put(1, "m1");
        a.put(2, "m2");
        a.put(3, "m3");
        a.put(4, "m4");
        a.put(5, "m5");
        a.put(6, "m6");
        Collection<String> b = a.values();
        System.out.println(b);

        for (Iterator i = b.iterator(); i.hasNext(); ) {
            if (i.next() != null) {
                i.remove();
            }
        }

        System.out.println(b);

        a.put(1, "m1");
        a.put(2, "m2");
        a.put(3, "m3");
        a.put(4, "m4");
        a.put(5, "m5");
        a.put(6, "m6");


        System.out.println(b);

        b.clear();

        System.out.println(b);

        HashMap c = new HashMap();
        c.put(1, "m1");
        System.out.println(c.values());
    }


}
