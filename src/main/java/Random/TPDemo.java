package Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TPDemo {

    public static void threadDemo() {
        ThreadFactory customThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        RejectedExecutionHandler rjh = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("task denied" + r.toString());
            }
        };

        // TPE using runnable, so no return value
       ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                1, 20, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                customThreadFactory, rjh
       );

       List<Future<?>> futuresList = new ArrayList<>();

       for (int i = 0; i < 20; i++) {
           int i2 = i;
           Future<?> result = tpe.submit(() -> {
               System.out.println("hello this is a task, and I am waiting " +
                       Thread.currentThread().threadId() + " " + i2);
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           });
           futuresList.add(result);
       }

        for (Future<?> f : futuresList) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Shutting down Executor 1");

        tpe.shutdownNow();

        // TPE using callable
        ThreadPoolExecutor tpe2 = new ThreadPoolExecutor(
                1, 20, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                customThreadFactory, rjh
        );

        List<Future<Integer>> futuresList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int i2 = i;
            Future<Integer> future = tpe2.submit(
                    () -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("hello this is a task, and I am waiting " +
                                Thread.currentThread().threadId() + " " + i2);
                        return i2;
                    }
            );

            futuresList2.add(future);
        }

        for (Future<Integer> future: futuresList2) {
            try {
                System.out.println("Future: " + future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
           }
        }

        System.out.println("Shutting down Executor 2");
    }
}
