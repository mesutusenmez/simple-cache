package com.example;

public abstract interface Cache<T> {
    
    public abstract void put(T value);

    public abstract T get();

    public abstract void remove();

}
