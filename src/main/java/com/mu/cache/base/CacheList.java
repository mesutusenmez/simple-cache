package com.mu.cache.base;

public abstract interface CacheList<T> extends Cache {
    
    public void put(String key, T value);

    public T get(String key);

    public void remove(String key);

    public int size();

}
