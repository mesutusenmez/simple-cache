package com.mu.cache.impl;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import com.mu.cache.base.CacheList;
import com.mu.cache.deamon.CacheTimer;

/**
 * <p>InMemoryCacheList class performs to keep a cache list in memory. You can create any type of cache list. </p>
 * <p>Example usage: <b> CacheList<String, String> caches = new InMemoryCacheList<>(2000); </b></p>
 * 
 * @author Mesut Usenmez
 * @version 1.0.0
 * 
 */
public final class InMemoryCacheList<T> implements CacheList<T> {

    private final ConcurrentHashMap<String, SoftReference<T>> caches = new ConcurrentHashMap<String, SoftReference<T>>();

    private CacheTimer<T> cacheTimer; 

    /**
     * It runs a deamon thread per second to remove all caches when the given time is up
     * 
     * @param timeToLive time of caches to live
     */
    public InMemoryCacheList(int timeToLive) {
        cacheTimer = new CacheTimer<>(this, timeToLive);
        cacheTimer.start();
    }

    /**
     * put a key-value pair as cache to cache list
     * 
     * @param key the key in cache to access
     * @param value the value in cache
     */
    @Override
    public void put(String key, T value) {
        caches.put(key, new SoftReference<T>(value));
        cacheTimer.extendExpireTime();
    }

    /**
     * get the value by key
     * 
     * @param key the key in cache to access
     * @return the value in cache
     */
    @Override
    public T get(String key) {
        if(caches.get(key) == null) {
            return null;
        }
        cacheTimer.extendExpireTime();
        return caches.get(key).get();
    }

    /**
     * remove cache by key
     * 
     * @param key key of the value in cache list
     */
    @Override 
    public void remove(String key) {
        caches.remove(key);
    }

    /**
     * clear all caches in cache list
     */
    @Override
    public void clear() {
        caches.clear();
    }

    /**
     * get size of cache list
     * 
     * @return size of cache list
     */
    @Override
    public int size() {
        return caches.size();
    }

    /**
     * check if cache list is empty
     * 
     * @return if size of the cache list is equal to 0, return false, otherwise return true
     */
    @Override
    public boolean isEmpty() {
        if(this.size() > 0) return false;
        return true;
    }
    
}