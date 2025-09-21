package LockDemos;

import LockImplementations.SpinLock;

public class SpinLockDemo {

    static int sharedResource = 0;
    static SpinLock spinLock = new SpinLock();

    public static void demo() throws InterruptedException {

        // create runnable
        Runnable runnable = new Runnable() {
            public void run() {
                for  (int i = 0; i < 10; i++) {
                    spinLock.lock();
                    sharedResource++;
                    System.out.println(Thread.currentThread().getName() + " incremented the shared resource");
                    spinLock.unlock();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            public void run() {
                for  (int i = 0; i < 10; i++) {
                    spinLock.lock();
                    sharedResource++;
                    System.out.println(Thread.currentThread().getName() + " incremented the shared resource");
                    spinLock.unlock();
                }
            }
        };

        // create threads + start them
        Thread t1 = new Thread(runnable, "thread-1");
        Thread t2 = new Thread(runnable2, "thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
