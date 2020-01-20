package task2;

import java.util.ArrayList;
import java.util.List;

public class CountingSemaphoreExecutioner {

    public static void executeCounterUsingCountingSemaphore() throws InterruptedException {
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
