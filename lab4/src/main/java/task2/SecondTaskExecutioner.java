package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SecondTaskExecutioner {

    public static void execute() {
        int numberOfElements = 1000;
        int numberOfProducers = 10;
        int numberOfConsumers = 10;

//        executeNaive(numberOfElements, numberOfProducers, numberOfConsumers);
        executeBetter(numberOfElements, numberOfProducers, numberOfConsumers);
    }

    private static void executeNaive(int numberOfElements, int numberOfProducers, int numberOfConsumers) {
        Buffer naiveBuffer = new NaiveBuffer(numberOfElements);

        Producer[] producers = getInitializedProducers(naiveBuffer, numberOfProducers);
        Consumer[] consumers = getInitializedConsumers(naiveBuffer, numberOfConsumers);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(0, 10)
                .forEach(i -> {
                    executorService.submit(producers[i]);
                    executorService.submit(consumers[i]);
                });

        executorService.shutdown();
    }

    private static void executeBetter(int numberOfElements, int numberOfProducers, int numberOfConsumers) {
        Buffer betterBuffer = new BetterBuffer(numberOfElements);

        Producer[] producers = getInitializedProducers(betterBuffer, numberOfProducers);
        Consumer[] consumers = getInitializedConsumers(betterBuffer, numberOfConsumers);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(0, 10)
                .forEach(i -> {
                    executorService.submit(producers[i]);
                    executorService.submit(consumers[i]);
                });

        executorService.shutdown();
    }

    private static Producer[] getInitializedProducers(Buffer buffer, int numberOfProducers) {
        return IntStream.range(0, numberOfProducers)
                .mapToObj(i -> new Producer(buffer))
                .toArray(Producer[]::new);
    }

    private static Consumer[] getInitializedConsumers(Buffer buffer, int numberOfConsumers) {
        return IntStream.range(0, numberOfConsumers)
                .mapToObj(i -> new Consumer(buffer))
                .toArray(Consumer[]::new);
    }


}
