package com.company;

import java.util.*;


public class LastTouchMapImpl<K, V> implements LastTouchMap<K, V> {

    private static final int BIN_COUNT = 1000;
    Set<K> keySet = null;
    private Object[] entries = new Object[BIN_COUNT];
    private int size = 0;
    private Entry head = null;
    private Collection<V> valueCollection = null;




    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        if (head == null) return true;
        return false;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        int index = hash(key);
        while (entries[index] != null && index < BIN_COUNT) {
            if (((Entry) entries[index]).getKey().equals(key)) {
                return true;
            }
            index++;
        }
        return false;
    }


    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        Entry entry = head;
        while (entry.getNext() != null) {
            if (entry.getValue().equals(value)) return true;
            entry = entry.getNext();
        }
        return false;

    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * <p/>
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p/>
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) {
        int index = hash(key);
        while (entries[index] != null && index < BIN_COUNT) {
            if (((Entry) entries[index]).getKey().equals(key)) {
                return (V) ((Entry) entries[index]).getValue();
            }
            index++;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
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
                    head.setPrevious(null);
                    return value;
                }
            }
            if (entry.getNext() != null && entry.getPrevious() != null) {
                entry.getPrevious().setNext(entry.getNext());
                entry.getNext().setPrevious(entry.getPrevious());
                entry.setNext(head);
                head.setPrevious(entry);
                head = entry;
                head.setPrevious(null);
                return value;
            }
            return value;
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
        return value;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     * <p/>
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     * <p/>
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     * <p/>
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        int index = hash(key);
        while (entries[index] != null && index < BIN_COUNT) {
            if (((Entry) entries[index]).getKey().equals(key)) {
                Entry entry = (Entry) entries[index];

                if (entry.getNext() != null && entry.getPrevious() != null) {
                    entry.getNext().setPrevious(entry.getPrevious());
                    entry.getPrevious().setNext(entry.getNext());
                } else if (entry.getNext() != null) {
                    entry.getNext().setPrevious(null);
                    head = entry.getNext();
                } else if (entry.getPrevious() != null) {
                    entry.getPrevious().setNext(null);
                } else {
                    head = null;
                }

                V value = (V) entry.getValue();
                entries[index] = null;
                size--;
                return value;
            }
            index++;
        }
        return null;
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        if (head == null) return;

        while (head.getNext() != null) {
            head.getNext().setPrevious(null);
            Entry entry = head;
            head = entry.getNext();
            entry.setNext(null);
        }

        head = null;
        Arrays.fill(entries, null);
        size = 0;

    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        Collection<V> ks = valueCollection;
        return (ks != null ? ks : (valueCollection = new Values()));
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    Set<Entry> entrySet;
    @Override
    public Set<Map.Entry<K, V>> entrySet() { //TODO
        Set<Entry> es;
        return null;//(es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public final int size() {
            return size;
        }

        public final void clear() {
            LastTouchMapImpl.this.clear();
        }

        public final Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        final class EntryIterator extends HashIterator implements Iterator {
            public final Entry next() {
                return nextEntry();
            }
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
            list.add((V) iter.getValue());
            try {
                iter = iter.getNext();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.reverse(list);
        return list;
    }

    private int hash(Object key) {
        return key.hashCode() % BIN_COUNT;
    }

    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return size;
        }

        public boolean contains(Object o) {
            return containsKey(o);
        }

        public boolean remove(Object o) {
            return LastTouchMapImpl.this.remove(o) != null;
        }

        public void clear() {
            LastTouchMapImpl.this.clear();
        }
    }

    private final class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return size;
        }

        public boolean contains(Object o) {
            return containsValue(o);
        }

        public void clear() {
            LastTouchMapImpl.this.clear();
        }
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry next;
        Entry current;

        HashIterator() {
            if (head != null) {
                current = null;
                next = head;
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<K, V> nextEntry() {
            Entry<K, V> e = next;
            current = next;
            next = e.getNext();
            return e;
        }

        public void remove() {
            if (current == null)
                throw new IllegalStateException();
            K key = (K) current.getKey();
            LastTouchMapImpl.this.remove(key);
        }


    }

    private final class ValueIterator extends HashIterator<V> {
        public V next() {
            return (V) nextEntry().value;
        }
    }

    private final class KeyIterator extends HashIterator<K> {
        public K next() {
            return (K) nextEntry().getKey();
        }
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry previous = null;
        private Entry next = null;

        public Entry getPrevious() {
            return previous;
        }

        public void setPrevious(Entry previous) {
            this.previous = previous;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public Entry(K key, V value, Entry next, Entry previous) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.previous = previous;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }

}
