package com.mu.cache.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mu.cache.base.SingleCache;
import com.mu.cache.configuration.Config;

public class ImMemoryCacheTest {
    
    private SingleCache<String> cache;

    private Config config;

    @Before
    public void setUp() {
        config = new Config.ConfigBuilder(2).build();
        cache = new InMemoryCache<>(config);
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

    @Test
    public void test_putManyValues_andThenBeforeAndAfter3secondsCompareExpectedValue() throws InterruptedException {
        cache.put("value");
        assertEquals("value", cache.get());
        Thread.sleep(1500);
        cache.put("value");
        assertEquals("value", cache.get());
        Thread.sleep(1500);
        cache.put("value");
        assertEquals("value", cache.get());
        Thread.sleep(1500);
        cache.get();
        assertEquals("value", cache.get());
        Thread.sleep(3000);
        assertEquals(null, cache.get());
    }

}
