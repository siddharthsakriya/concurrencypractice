package ConcurrencyProblems;

public class ProducerConsumerDemo {
    public static void demo() throws InterruptedException {

        ProducerConsumer prodCom = new ProducerConsumer();

        // create runnables
        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    prodCom.producerSynchronised();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    prodCom.consumerSynchronised();
                }
            }
        };

        // create threads + start them
        Thread t1 = new Thread(runnable, "producer-thread");
        Thread t2 = new Thread(runnable2, "consumer-thread");
        Thread t3 = new Thread(runnable, "producer-thread-2");
        Thread t4 = new Thread(runnable2, "consumer-thread-2");


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
