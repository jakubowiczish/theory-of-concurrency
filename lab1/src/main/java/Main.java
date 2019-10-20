import task1.Counter;
import task2.CounterSynchronized;
import task3.Buffer;
import task3.Consumer;
import task3.Producer;

public class Main {

    private static final int COUNTER = 10000000;

    public static void main(String[] args) throws InterruptedException {
//        testCounter();
//        testCounterSynchronized();

        testProducerConsumer();
    }

    private static void testCounter() throws InterruptedException {
        Counter counter = new Counter();

        Thread first = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counter.increment();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counter.decrement();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println("Unsynchronized counter: " + counter.getCounter());
    }

    private static void testCounterSynchronized() throws InterruptedException {
        CounterSynchronized counterSynchronized = new CounterSynchronized();

        Thread first = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counterSynchronized.increment();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counterSynchronized.decrement();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println("Synchronized counter: " + counterSynchronized.getCounter());
    }

    private static void testProducerConsumer() {
        Buffer buffer = new Buffer();
        (new Thread(new Producer(buffer))).start();
//        new Thread(new task3.Producer(buffer)).start();
        (new Thread(new Consumer(buffer))).start();
    }
}
