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

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryAcquire(int acquires){
        return sync.tryAcquire(acquires);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.unlock();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static final class MySync extends AbstractQueuedSynchronizer {
        final void lock() {
            acquire(1);
        }

        final void unlock() {
            setExclusiveOwnerThread(null);
            acquire(0);
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            final Thread thread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (!hasQueuedThreads() && compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(thread);
                    return true;
                } else if (thread == getExclusiveOwnerThread()) {
                    state = state + acquires;
                    if (state < 0) {
                        new Error("出错了！");
                    }
                    setState(state);
                    return true;
                }
            }
            return false;
        }
    }
}
