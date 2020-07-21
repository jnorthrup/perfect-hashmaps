package org.perfecthashmap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p><code>PerfectHashmap</code> is optimized for fast <code>get</code> and <code>contains</code> operations.
 * It is required that the set of keys is finite, known in advance and the hashcodes for the keys are collision free.</p>
 * <p>Only use this implementation if your application is latency sensitive.</p>
 *
 * @param <K> Type parameter for keys.
 * @param <V> Type parameter for values.
 * @author Nicholas Poul Schultz-M?ller
 */
public class PerfectArrayMap<K, V> implements Map<K, V> {
    Object[][] kv;

    PerfectArrayMap(Object[]... kv1) {
        kv = kv1;
    }

    /**
     * Calculates the index given the key and the size (of the map).
     *
     * @param key  The key to find the index for.
     * @param size The size of the map. Must be a power of 2.
     * @return The <code>size</code> least significant bits of the <code>key</code>'s hashcode.
     */
    private static int indexFor(Object key, int size) {
        return key.hashCode() & (size - 1);
    }


    @Override
    public boolean containsKey(Object key) {
        return Arrays.stream(kv).anyMatch(kve -> key.equals(kve[0]));
    }

    @Override
    public boolean containsValue(Object value) {
        return Arrays.stream(kv).anyMatch(kve -> value.equals(kve[1]));
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        return (V) getValues()[indexFor(key)];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result;

        var keys = getKeys();
        result = Arrays.stream(keys).<Entry<K, V>>map(key -> new Entry<>() {
            @SuppressWarnings("unchecked")
            @Override
            public K getKey() {
                return (K) key;
            }

            @SuppressWarnings("unchecked")
            @Override
            public V getValue() {
                return (V) PerfectArrayMap.this.getValues()[PerfectArrayMap.this.indexFor(key)];
            }

            @SuppressWarnings("unchecked")
            @Override
            public V setValue(V value) {
                throw new UnsupportedOperationException();
            }
        }).collect(Collectors.toCollection(() -> new LinkedHashSet<>(getKeys().length)));

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keySet() {
        return Arrays.stream(kv).map(objects -> (K) objects[0]).collect(Collectors.toSet());

    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(kv).map(objects -> (V) objects[1]).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) { throw new UnsupportedOperationException(); }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size = 0;

        for (Object o : getKeys()) {
            if (o != null) ++size;
        }

        return size;
    }

    private int indexFor(Object key) {
        return indexFor(key, getKeys().length);
    }

    public Object[] getKeys() {
        return Arrays.stream(kv).map(objects -> objects[0]).toArray();
    }

    public Object[] getValues() {
        return Arrays.stream(kv).map(objects -> objects[1]).toArray();
    }

}
