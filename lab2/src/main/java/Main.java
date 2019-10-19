import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int COUNTER = 100;

    public static void main(String[] args) throws InterruptedException {
//        testCounterUsingBinarySemaphore();
        testCounterUsingCountingSemaphore();
    }

    private static void testCounterUsingBinarySemaphore() throws InterruptedException {
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

    private static void testCounterUsingCountingSemaphore() throws InterruptedException {
        Shop shop = new Shop(5);

        System.out.println("Number of baskets in the beginning: " + shop.getBaskets());

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new Client(shop, i)));
        }

        for (int i = 0; i < 10; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < 10; i++) {
            threads.get(i).join();
        }

        System.out.println("Number of baskets in the end: " + shop.getBaskets());
    }
}
