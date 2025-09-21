package ConcurrencyProblems;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProducerConsumer {
    final int  MAX_VALUE = 5;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public synchronized void producerSynchronised(){
        if (queue.size() == MAX_VALUE){
            try { wait(); } catch (InterruptedException ignored) {}
        } else {
            queue.add(1);
            System.out.println("Produced 1 item, queue size: " + queue.size());
        }
        notifyAll();
    }

    public synchronized void consumerSynchronised(){
        if (queue.isEmpty()) {
            try { wait(); } catch (InterruptedException ignored) {}
        } else {
            queue.remove();
            System.out.println("Consumed 1 item, queue size: " + queue.size());
        }
    }

}
