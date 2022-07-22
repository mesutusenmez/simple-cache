package com.mu.cache.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mu.cache.base.SingleCache;

public class ImMemoryCacheTest {
    
    private SingleCache<String> cache;

    @Before
    public void setUp() {
        cache = new InMemoryCache<>(2);
    }

    @Test
    public void test_putValueToCache_andThenCompareExpectedValue() {
        cache.put("value");
        assertEquals("value", cache.get());
    }

    @Test
    public void test_removeValueFromCache_andThenCompareExpectedValue() {
        cache.put("value");
        cache.clear();
        assertEquals(null, cache.get());
    }

    @Test
    public void test_putValueToCache_andThenBeforeAndAfter3secondsCompareExpectedValue() throws InterruptedException {
        cache.put("value");
        assertEquals("value", cache.get());
        Thread.sleep(3000);
        assertEquals(null, cache.get());
    }

}
