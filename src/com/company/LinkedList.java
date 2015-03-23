package com.company;

import com.company.LinkedList;

/**
* Created by intern on 3/23/15.
*/

import java.util.List;


public class LinkedList<K,V> implements LastTouchMap<K,V> {
    private Node head;
    private int size;

    public LinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (head == null) {

        }
    }

    @Override
    public List<V> getLast(int n) {
        List
        if (head == null) {
            return 0;
        }
    }

    private class Node<K,V> {
        Node next;
        K id;
        V name;

        public Node() {

        }

        public Node(Node next, K id, V name) {
            this.next = next;
            this.id = id;
            this.name = name;
        }
    }
}
