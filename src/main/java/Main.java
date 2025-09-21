import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
         LockDemos.SpinLockDemo.demo();
         sleep(1000);
         ConcurrencyProblems.ProducerConsumerDemo.demo();
    }
}
