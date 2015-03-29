package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LastTouchMapImpl<Integer, String> listMap = new LastTouchMapImpl<Integer, String>();
        listMap.put(10,"ceva");
        listMap.put(2,"altceva");
        listMap.put(2,"acltceva");
        listMap.put(10,"axceva");
        listMap.put(3,"axceva2");
        listMap.put(4,"axceva3");
        List<String> list;
        list = listMap.getLast(4);
        for (Object iter : list) {
            System.out.println(iter);
        }
        System.out.println(listMap.findEntry(2));
    }


}
