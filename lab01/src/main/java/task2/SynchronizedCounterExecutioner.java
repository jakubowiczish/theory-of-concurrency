package task2;

public class SynchronizedCounterExecutioner {

    private static final int COUNTER = 10000000;

    public static void executeCounterSynchronized() throws InterruptedException {
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
}
