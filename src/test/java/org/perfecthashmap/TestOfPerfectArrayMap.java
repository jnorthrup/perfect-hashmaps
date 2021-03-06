package org.perfecthashmap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestOfPerfectArrayMap {

    private Map<Integer, String> emptyMap;
    private Map<Integer, String> oneElementMap;
    private Map<Integer, String> twoElementMap;
    private Map<Integer, String> threeElementMap;

    @Before
    public void setup() {
        emptyMap = new PerfectArrayMap();
        oneElementMap = new PerfectArrayMap(new Object[]{1, "a"});
        twoElementMap = new PerfectArrayMap(new Object[]{2, "b"}, new Object[]{3, "c"});
        threeElementMap = new PerfectArrayMap(new Object[]{4, "d"}, new Object[]{5, "e"}, new Object[]{6, "f"});
    }

    @Test
    public void testEmpty() {
        assertTrue(emptyMap.isEmpty());

        assertFalse(oneElementMap.isEmpty());
        assertFalse(twoElementMap.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(emptyMap.size(), 0);

        assertEquals(oneElementMap.size(), 1);
        assertEquals(twoElementMap.size(), 2);
    }

    @Test
    public void testContainsKey() {
        assertTrue(oneElementMap.containsKey(1));

        Object key = new Object();
        Map<Object, Object> map = new PerfectArrayMap(new Object[]{key, null});

        assertTrue(map.containsKey(key));

//        map.remove(key);
//        assertFalse(map.containsKey(key));
    }

    @Test
    public void testContainsValue() {
        Object key = new Object();
        Map<Object, Integer> map = new PerfectArrayMap(new Object[]{key, 1}) ;

        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(2));
    }

    @Test
    public void testGet() {
        assertEquals("a", oneElementMap.get(1));
        assertEquals("b", twoElementMap.get(2));
        assertEquals("c", twoElementMap.get(3));
    }

  /*  @Test
    public void testClear() {
        assertTrue(emptyMap.isEmpty());
        emptyMap.clear(); // Should be no Op.
        assertTrue(emptyMap.isEmpty());

        oneElementMap.clear();
        assertTrue(oneElementMap.isEmpty());

        twoElementMap.clear();
        assertTrue(twoElementMap.isEmpty());
    }
*/
/*
    @Test
    public void testPut() {
        String oldValue = twoElementMap.put(2, "b2");

        assertEquals("b", oldValue);
        assertEquals(2, twoElementMap.size());
        assertEquals("b2", twoElementMap.get(2));
    }
*/

/*
    @Test
    public void testPutWithWrongKey() {
        try {
            twoElementMap.put(9, "x");

            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot insert key '9' because it's a new key.", e.getMessage());
        }
    }
*/

/*
    @Test
    public void testPutAll() {
        Map<Integer, String> newEntries = new HashMap<Integer, String>();
        newEntries.put(2, "b2");
        newEntries.put(3, "c2");

        twoElementMap.putAll(newEntries);

        assertEquals(2, twoElementMap.size());
        assertEquals("b2", twoElementMap.get(2));
        assertEquals("c2", twoElementMap.get(3));
    }
*/

  /*  @Test
    public void testPutAllWithWrongKey() {
        Map<Integer, String> newEntries = new HashMap<Integer, String>();
        newEntries.put(9, "x");

        try {
            twoElementMap.putAll(newEntries);

            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot insert key '9' because it's a new key.", e.getMessage());
        }
    }*/

    @Test
    public void testKeys() {
        assertEquals(Collections.EMPTY_SET, emptyMap.keySet());

        assertArrayEquals(new Integer[]{1}, oneElementMap.keySet().toArray());

        assertArrayEquals(new Integer[]{2, 3}, twoElementMap.keySet().toArray());

        assertArrayEquals(new Integer[]{4, 5, 6}, threeElementMap.keySet().toArray());
    }

    @Test
    public void testValues() {
        assertEquals(Collections.EMPTY_LIST, emptyMap.values());

        assertArrayEquals(new String[]{"a"}, oneElementMap.values().toArray());

        assertArrayEquals(new String[]{"b", "c"}, twoElementMap.values().toArray());

        assertArrayEquals(new String[]{"d", "e", "f"}, threeElementMap.values().toArray());
    }

    @Test
    public void testEntrySet() {
        Set<Map.Entry<Integer, String>> entrySet;

        entrySet = emptyMap.entrySet();
        assertTrue(entrySet.isEmpty());

        entrySet = oneElementMap.entrySet();
        assertEquals(1, entrySet.size());
        Map.Entry<Integer, String> entry = entrySet.iterator().next();
        assertEquals(1, (int) entry.getKey());
        assertEquals("a", entry.getValue());

        entrySet = twoElementMap.entrySet();
        assertEquals(2, entrySet.size());
        Iterator<Map.Entry<Integer, String>> iter = twoElementMap.entrySet().iterator();
        entry = iter.next();
        assertEquals(2, (int) entry.getKey());
        assertEquals("b", entry.getValue());
        entry = iter.next();
        assertEquals(3, (int) entry.getKey());
        assertEquals("c", entry.getValue());
    }

}
