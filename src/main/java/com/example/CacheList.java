package com.example;

public abstract interface CacheList<K, T> {
    
    public abstract void put(K key, T value);

    public abstract T get(K key);

    public abstract void remove(K key);

    public abstract void clear();

    public abstract int size();

}
