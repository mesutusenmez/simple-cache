package com.example;

import java.lang.ref.SoftReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>InMemoryCache class performs to keep a single cache in memory. You can create any type of cache object. </p>
 * <p>Example usage: <b> Cache<String> cache = new InMemoryCache<>(2000); </b></p>
 * 
 * @author Mesut Usenmez
 * @version 1.0.0
 * 
 */
public final class InMemoryCache<T> implements Cache<T> {

    private  AtomicReference<SoftReference<T>> cache = new AtomicReference<>();

    /**
     * It runs a deamon thread per second to remove the cache object when the given time is up
     * 
     * @param timeToLive time of cache object to live
     */
    public InMemoryCache(long timeToLive) {

        long expireTime  = System.currentTimeMillis() + timeToLive;

        if(timeToLive > 0) {
            Thread thread = new Thread(()-> {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        long now = System.currentTimeMillis();
                        if(now > expireTime) {
                            this.remove();
                        }

                        if(this.get() == null) break;
                        
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
     * remove the value in cache
     */
    @Override
    public void remove() {
        cache.set(null);
    }
    
}
