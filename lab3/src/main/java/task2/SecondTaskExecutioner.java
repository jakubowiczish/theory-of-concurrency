package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SecondTaskExecutioner {

    private static final int NUMBER_OF_CUSTOMERS = 5;

    public static void executePrintersMonitor() throws InterruptedException {
        PrintersMonitor printersMonitor = new PrintersMonitor(3);
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CUSTOMERS);

        IntStream.range(0, NUMBER_OF_CUSTOMERS)
                .mapToObj(i -> new Customer(i, printersMonitor))
                .forEach(executorService::submit);

        executorService.shutdown();
    }
}
