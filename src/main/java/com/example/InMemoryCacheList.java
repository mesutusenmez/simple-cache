package com.example;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCacheList<K, T> implements CacheList<K, T> {

    private final ConcurrentHashMap<K, SoftReference<T>> caches = new ConcurrentHashMap<K, SoftReference<T>>();


    public InMemoryCacheList(long timeToLive) {
        
        long expireTime  = System.currentTimeMillis() + timeToLive;

        if(timeToLive > 0) {
            Thread thread = new Thread(()-> {
                while(true) {
                    long now = System.currentTimeMillis();
                  
                    try {
                        Thread.sleep(1000);
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

    @Override
    public void put(K key, T value) {
        caches.put(key, new SoftReference<T>(value));
    }

    @Override
    public T get(K key) {
        if(caches.get(key) == null) {
            return null;
        }
        return caches.get(key).get();
    }

    @Override
    public void remove(K key) {
        caches.remove(key);
    }

    @Override
    public void clear() {
        caches.clear();
    }

    @Override
    public int size() {
        return caches.size();
    }
    
}
