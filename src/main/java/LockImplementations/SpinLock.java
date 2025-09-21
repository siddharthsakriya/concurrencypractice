package LockImplementations;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
    AtomicBoolean lock = new AtomicBoolean(false);
    public void lock() {
        while (!lock.compareAndExchange(false, true)) {
            //spin
        }
    }

    public void unlock() {
        lock.set(false);
    }
}
