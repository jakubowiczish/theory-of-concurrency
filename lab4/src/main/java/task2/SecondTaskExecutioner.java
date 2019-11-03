package task2;

import java.util.stream.IntStream;

import static task2.TimeToFileSaver.initializeDataCollecting;
import static task2.TimeToFileSaver.saveCollectedData;

public class SecondTaskExecutioner {

    private static final int numberOfElements = 100000;
    private static final int numberOfProducersConsumers = 10;

    public static void execute() {

        executeNaive(numberOfElements, numberOfProducersConsumers);
//        executeBetter(numberOfElements, numberOfProducersConsumers);
    }

    private static void executeNaive(int bufferSize, int numberOfProducersConsumers) {
        initializeDataCollecting(bufferSize, numberOfProducersConsumers, true);

        Buffer naiveBuffer = new NaiveBuffer(bufferSize);

        Producer[] producers = getInitializedProducers(naiveBuffer, numberOfProducersConsumers);
        Consumer[] consumers = getInitializedConsumers(naiveBuffer, numberOfProducersConsumers);

        runProcesses(producers, consumers);

        saveCollectedData();
    }

    private static void executeBetter(int bufferSize, int numberOfProducersConsumers) {
        initializeDataCollecting(bufferSize, numberOfProducersConsumers, false);

        Buffer betterBuffer = new BetterBuffer(bufferSize);

        Producer[] producers = getInitializedProducers(betterBuffer, numberOfProducersConsumers);
        Consumer[] consumers = getInitializedConsumers(betterBuffer, numberOfProducersConsumers);

        runProcesses(producers, consumers);

        saveCollectedData();
    }

    private static Producer[] getInitializedProducers(Buffer buffer, int numberOfProducers) {
        return IntStream.range(0, numberOfProducers)
                .mapToObj(i -> new Producer(buffer))
                .toArray(Producer[]::new);
    }

    private static void runProcesses(Producer[] producers, Consumer[] consumers) {
        IntStream.range(0, numberOfProducersConsumers).forEach(i -> {
            producers[i].start();
            consumers[i].start();
        });

        sleep(4000);

        IntStream.range(0, numberOfProducersConsumers).forEach(i -> {
            producers[i].interrupt();
            consumers[i].interrupt();
        });
    }

    private static Consumer[] getInitializedConsumers(Buffer buffer, int numberOfConsumers) {
        return IntStream.range(0, numberOfConsumers)
                .mapToObj(i -> new Consumer(buffer))
                .toArray(Consumer[]::new);
    }

    private static void sleep(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
