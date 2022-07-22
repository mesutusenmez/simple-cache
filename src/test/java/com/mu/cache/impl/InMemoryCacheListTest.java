package com.mu.cache.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mu.cache.base.CacheList;

public class InMemoryCacheListTest {
    
    private CacheList<String> caches;

    @Before
    public void setUp() {
        caches = new InMemoryCacheList<>(2);
    }

    @Test
    public void test_putCachesToCacheList_andThenCompareExpectedCacheListSize() {
        caches.put("key1", "value1");
        caches.put("key2", "value2");
        assertEquals(2, caches.size());
    }

    @Test
    public void test_putCacheToCacheList_andThenCompareExpectedCacheValue() {
        caches.put("key1", "value1");
        assertEquals("value1", caches.get("key1"));
    }

    @Test
    public void test_removeCacheFromCacheList_andThenCompareExpectedCacheListSize() {
        caches.put("key1", "value1");
        caches.put("key2", "value2");
        caches.remove("key1");
        assertEquals(1, caches.size());
    }

    @Test
    public void test_removeCacheFromCacheList_andThenCompareRemovedCacheValue() {
        caches.put("key1", "value1");
        caches.put("key2", "value2");
        caches.remove("key1");
        assertEquals(null, caches.get("key1"));
    }

    @Test
    public void test_putCacheToCacheList_andThenBeforeAndAfter3secondsCompareCacheListSize() throws InterruptedException {
        caches.put("key1", "value1");
        caches.put("key2", "value2");
        caches.put("key3", "value3");
        assertEquals(3, caches.size());
        Thread.sleep(3000);
        assertEquals(0, caches.size());
    }

}
