package com.example;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>InMemoryCacheList class performs to keep a cache list in memory. You can create any type of cache list. </p>
 * <p>Example usage: <b> CacheList<String, String> caches = new InMemoryCacheList<>(2000); </b></p>
 * 
 * @author Mesut Usenmez
 * @version 1.0.0
 * 
 */
public final class InMemoryCacheList<K, T> implements CacheList<K, T> {

    private final ConcurrentHashMap<K, SoftReference<T>> caches = new ConcurrentHashMap<K, SoftReference<T>>();

    /**
     * It runs a deamon thread per second to remove all caches when the given time is up
     * 
     * @param timeToLive time of caches to live
     */
    public InMemoryCacheList(long timeToLive) {
        
        long expireTime  = System.currentTimeMillis() + timeToLive;

        if(timeToLive > 0) {
            Thread thread = new Thread(()-> {
                while(true) {  
                    try {
                        Thread.sleep(1000);
                        long now = System.currentTimeMillis();
                        if(now > expireTime) {
                            this.clear();
                        }
                        if(this.size() <= 0) break;
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * put a key-value pair as cache to cache list
     * 
     * @param key the key in cache to access
     * @param value the value in cache
     */
    @Override
    public void put(K key, T value) {
        caches.put(key, new SoftReference<T>(value));
    }

    /**
     * get the value by key
     * 
     * @param key the key in cache to access
     * @return the value in cache
     */
    @Override
    public T get(K key) {
        if(caches.get(key) == null) {
            return null;
        }
        return caches.get(key).get();
    }

    /**
     * remove cache by key
     * 
     * @param key key of the value in cache list
     */
    @Override 
    public void remove(K key) {
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
    
}