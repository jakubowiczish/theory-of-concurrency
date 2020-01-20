package task1;

import java.util.ArrayList;
import java.util.List;

public class FirstTaskExecutioner {

    private static final int AMOUNT = 100;
    private static final int PRODUCERS_COUNTER = 2;
    private static final int CONSUMERS_COUNTER = 1;

    public static void executeBoundedBufferOnProducersAndConsumers() throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();

        for (int i = 0; i < PRODUCERS_COUNTER; i++) {
            producers.add(new Thread(new Producer(boundedBuffer, AMOUNT, i)));
        }

        for (int i = 0; i < CONSUMERS_COUNTER; i++) {
            consumers.add((new Thread(new Consumer(boundedBuffer, 5 * AMOUNT, i))));
        }

        for (int i = 0; i < PRODUCERS_COUNTER; i++) {
            producers.get(i).start();
        }

        for (int i = 0; i < CONSUMERS_COUNTER; i++) {
            consumers.get(i).start();
        }

        for (int i = 0; i < PRODUCERS_COUNTER; i++) {
            producers.get(i).join();
        }

        for (int i = 0; i < CONSUMERS_COUNTER; i++) {
            consumers.get(i).join();
        }
    }
}
