package com.mu.cache.deamon;

import com.mu.cache.base.Cache;

public final class CacheTimer<T> extends Thread {

    private Cache cache;
    private long expireTime;
    private int timeToLive;
    private TimerState state = TimerState.NEW;

    public CacheTimer(Cache cache, int timeToLive) {
        this.cache = cache;
        this.expireTime  = System.currentTimeMillis() + timeToLive*1000;
        this.timeToLive = timeToLive;
        this.setDaemon(true);

    }

    @Override
    public void run() {
        while(!TimerState.STOPED.equals(state)) {
            try {
                Thread.sleep(300);
                long now = System.currentTimeMillis();
                if(now > this.expireTime) {
                    cache.clear();
                    state = TimerState.STOPED;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void extendExpireTime() {
        this.expireTime  = System.currentTimeMillis() + timeToLive*1000;
        this.state = TimerState.RESTART;
    }

}
