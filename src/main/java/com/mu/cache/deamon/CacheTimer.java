package com.mu.cache.deamon;

import com.mu.cache.base.Cache;

public final class CacheTimer<T> extends Thread {

    private Cache cache;

    private long expireTime;

    public CacheTimer(Cache cache, int timeToLive) {
        this.cache = cache;
        this.expireTime  = System.currentTimeMillis() + timeToLive*1000;
        this.setDaemon(true);
    }

    @Override
    public void run() {

        while(true) {
            try {
                Thread.sleep(300);
                long now = System.currentTimeMillis();
                if(now > this.expireTime) cache.clear();
                if(cache.isEmpty()) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    
}
