package com.cz;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : zhang.cao
 * @date : 2020/9/22 18:26
 */
public class MyLock implements Lock {
    private final MySync sync;

    public MyLock() {
        sync = new MySync();
    }

    public void lock() {

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {

    }

    public Condition newCondition() {
        return null;
    }

    static final class MySync extends AbstractQueuedSynchronizer {

    }
}
