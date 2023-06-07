package com.github.tvloet1.seacleaner.entities;

import com.github.hanyaeger.api.Timer;

public class FireTimer extends Timer {
    public FireTimer(long intervalInMs) {
        super(intervalInMs);
        resume();
        System.out.println(System.currentTimeMillis());
        handle(System.currentTimeMillis());
        handle(System.currentTimeMillis());
        handle(System.currentTimeMillis());
    }

    @Override
    public void onAnimationUpdate(long l) {
        System.out.println("Timer says: " + l);
    }
}
