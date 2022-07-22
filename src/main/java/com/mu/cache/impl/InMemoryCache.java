package com.mu.cache.impl;

import java.lang.ref.SoftReference;
import java.util.concurrent.atomic.AtomicReference;
import com.mu.cache.base.SingleCache;
import com.mu.cache.deamon.CacheTimer;

/**
 * <p>InMemoryCache class performs to keep a single cache in memory. You can create any type of cache object. </p>
 * <p>Example usage: <b> Cache<String> cache = new InMemoryCache<>(2); </b></p>
 * 
 * @author Mesut Usenmez
 * @version 1.0.1
 * 
 */
public final class InMemoryCache<T> implements SingleCache<T> {

    private AtomicReference<SoftReference<T>> cache = new AtomicReference<>();

    private CacheTimer<T> cacheTimer; 

    /**
     * It runs a deamon thread per second to remove the cache object when the given time is up
     * 
     * @param timeToLive time(sec) of cache object to live
     */
    public InMemoryCache(int timeToLive) {
        cacheTimer = new CacheTimer<>(this, timeToLive);
        cacheTimer.start();
    }

    /**
     * put a value to the cache
     * 
     * @param value the value of generic type
     */
    @Override
    public void put(T value) {
        cache.set(new SoftReference<T>(value));
    }

    /**
     * get the value in cache
     * 
     * @return the cache value
     */
    @Override
    public T get() {
        if(cache.get() == null) return null;
        return cache.get().get();
    }

    /**
     * clear the value of cache 
     * 
     */
    @Override
    public void clear() {
        cache.set(null);
    }

    /**
     * check if the value of cache is empty
     * 
     * @return if the value of cache is not null, return false, otherwise return true
     */
    @Override
    public boolean isEmpty() {
        if(cache.get() != null && cache.get().get() != null) return false;
        return true;
    }
    
}
