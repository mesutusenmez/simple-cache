package com.mu.cache.base;

public abstract interface SingleCache<T> extends Cache {
    
    public void put(T value);

    public T get();

}
