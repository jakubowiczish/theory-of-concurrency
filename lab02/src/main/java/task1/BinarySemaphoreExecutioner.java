package task1;

public class BinarySemaphoreExecutioner {

    private static final int COUNTER = 100;

    public static void executeCounterUsingBinarySemaphore() throws InterruptedException {
        BinarySemaphore binarySemaphore = BinarySemaphore.getInstance();
        Counter counter = new Counter();

        Thread first = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                binarySemaphore.acquire();
                counter.increment();
                binarySemaphore.release();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                binarySemaphore.acquire();
                counter.decrement();
                binarySemaphore.release();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println("Unsynchronized counter: " + counter.getCounter());
    }
}
