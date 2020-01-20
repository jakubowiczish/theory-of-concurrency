package task1;

public class CounterExecutioner {

    private static final int COUNTER = 10000000;

    public static void executeCounter() throws InterruptedException {
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
}
