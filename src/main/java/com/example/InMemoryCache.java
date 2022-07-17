package com.example;

import java.lang.ref.SoftReference;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryCache<T> implements Cache<T> {

    private  AtomicReference<SoftReference<T>> cache = new AtomicReference<>();

    public InMemoryCache(long timeToLive) {
        long expireTime  = System.currentTimeMillis() + timeToLive;

        if(timeToLive > 0) {
            Thread thread = new Thread(()-> {
                while(true) {
                    long now = System.currentTimeMillis();
                  
                    try {
                        Thread.sleep(1000);
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

    @Override
    public void put(T value) {
        cache.set(new SoftReference<T>(value));
    }

    @Override
    public T get() {
        if(cache.get() == null) return null;
        return cache.get().get();
    }

    @Override
    public void remove() {
        cache.set(null);
    }
    
}
