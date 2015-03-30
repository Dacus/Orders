package com.company;

import java.util.ArrayList;
import java.util.List;


public class LastTouchMapImpl<K, V> implements LastTouchMap<K, V> {

    private static final int BIN_COUNT = 1000;
    private int size = 0;
    private final Object[] entries = new Object[BIN_COUNT];
    private Entry head = null;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);

        while ((entries[index] != null) && (((Entry) entries[index]).getKey() != key) && (index < BIN_COUNT)) {
            index++;
        }
        if (entries[index] != null) {
            Entry entry = (Entry) entries[index];
            entry.setValue(value);
            if (entry.getNext() == null) {
                if (entry.getPrevious() != null) {
                    entry.getPrevious().setNext(null);
                    entry.setNext(head);
                    head.setPrevious(entry);
                    head = entry;
                    return;
                }
            }
            if (entry.getNext() != null && entry.getPrevious() != null) {
                entry.getPrevious().setNext(entry.getNext());
                entry.getNext().setPrevious(entry.getPrevious());
                entry.setNext(head);
                head.setPrevious(entry);
                head = entry;
                return;
            }
            return;
        }
        if (head == null) {
            entries[index] = new Entry(key, value, null, null);
            head = (Entry) entries[index];
            size++;
        } else {
            entries[index] = new Entry(key, value, head, null);
            head.previous = (Entry) entries[index];
            head = (Entry) entries[index];
            size++;
        }
    }

    @Override
    public List<V> getLast(int n) {
        List<V> list = new ArrayList<V>();
        if (head == null) {
            return list;
        }
        Entry iter = head;
        for (int i = 1; (i <= n) && (i <= size); i++) {
            list.add(iter.getValue());
            try {
                iter = iter.getNext();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private int hash(K key) {
        return key.hashCode() % BIN_COUNT;
    }

    public Entry findEntry(K key) {
        int index = hash(key);
        while (entries[index] != null && index < BIN_COUNT) {
            if (((Entry) entries[index]).getKey() == key) {
                return (Entry) entries[index];
            }
            index++;
        }
        return null;
    }

    private class Entry {
        private final K key;
        private V value;
        private Entry previous = null;
        private Entry next = null;

        public Entry(K key, V value, Entry next, Entry previous) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "{key: " + key + "} {value: " + value + "}";
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry getPrevious() {
            return previous;
        }

        public void setPrevious(Entry previous) {
            this.previous = previous;
        }


    }

}
