package task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FirstTaskExecutioner {

    private static final int BUFFER_SIZE = 8;

    private static final int PROCESSING_COUNTER = 2;
    private static final int THREADS_COUNTER = PROCESSING_COUNTER + 2; // processing + consumer + producer

    public static void executePipeline() {
        Pipeline pipeline = new Pipeline(BUFFER_SIZE);
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNTER);

        executorService.submit(new Producer(pipeline));
        executorService.submit(new Consumer(pipeline, PROCESSING_COUNTER));

        Processing[] processing = new Processing[PROCESSING_COUNTER];
        IntStream.range(0, PROCESSING_COUNTER)
                .forEach(i -> {
                    processing[i] = new Processing(pipeline, i + 1);
                    executorService.submit(processing[i]);
                });

        executorService.shutdown();
    }
}
