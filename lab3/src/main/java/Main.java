import task1.BoundedBuffer;
import task1.Consumer;
import task1.Producer;
import task2.Customer;
import task2.PrintersMonitor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int AMOUNT = 100;
    private static final int producersCounter = 2;
    private static final int consumersCounter = 1;

    public static void main(String[] args) throws InterruptedException {
//        testBoundedBufferOnProducersAndConsumers();
        testPrintersMonitor();
    }

    private static void testPrintersMonitor() throws InterruptedException {
        PrintersMonitor printersMonitor = new PrintersMonitor(3);
        createTaskToPrint(printersMonitor, 5);
    }

    private static void createTaskToPrint(PrintersMonitor printersMonitor, int numberOfPrinters) throws InterruptedException {
        List<Thread> printers = new ArrayList<>();
        for (int i = 0; i < numberOfPrinters; i++) {
            printers.add(new Thread(new Customer(i, printersMonitor)));
        }

        for (int i = 0; i < numberOfPrinters; i++) {
            printers.get(i).start();
        }

        for (int i = 0; i < numberOfPrinters; i++) {
            printers.get(i).join();
        }
    }

    private static void testBoundedBufferOnProducersAndConsumers() throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();

        for (int i = 0; i < producersCounter; i++) {
            producers.add(new Thread(new Producer(boundedBuffer, AMOUNT, i)));
        }

        for (int i = 0; i < consumersCounter; i++) {
            consumers.add((new Thread(new Consumer(boundedBuffer, 5 * AMOUNT, i))));
        }

        for (int i = 0; i < producersCounter; i++) {
            producers.get(i).start();
        }

        for (int i = 0; i < consumersCounter; i++) {
            consumers.get(i).start();
        }

        for (int i = 0; i < producersCounter; i++) {
            producers.get(i).join();
        }

        for (int i = 0; i < consumersCounter; i++) {
            consumers.get(i).join();
        }
    }
}
