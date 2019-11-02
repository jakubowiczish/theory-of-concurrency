import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FourthTaskExecutioner {

    private static final int BUFFER_SIZE = 8;
    private static final int PROCESSORS_COUNTER = 2;
    private static final int THREADS_COUNTER = PROCESSORS_COUNTER + 2; // processors + consumer + producer

    public static void executePipeline() {
        ProcessSupplier processSupplier = new ProcessSupplier(createBuffer(), PROCESSORS_COUNTER);
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNTER);

        executorService.submit(processSupplier.getProducer());

        IntStream.range(0, PROCESSORS_COUNTER)
                .mapToObj(processSupplier::getProcessor)
                .forEach(executorService::submit);

        executorService.submit(processSupplier.getConsumer());

        executorService.shutdown();

    }

    private static List<Integer> createBuffer() {
        return IntStream.range(0, BUFFER_SIZE)
                .map(e -> 0)
                .boxed()
                .collect(toList());
    }
}
