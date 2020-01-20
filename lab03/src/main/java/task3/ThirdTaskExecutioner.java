package task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThirdTaskExecutioner {

    private static final int NUMBER_OF_PAIRS = 4;
    private static final int NUMBER_OF_GUESTS = NUMBER_OF_PAIRS * 2;
    private static final int NUMBER_OF_VISITS = 1;

    public static void executeWaitersMonitor() {
        WaitersMonitor monitor = new WaitersMonitor(NUMBER_OF_PAIRS);
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_GUESTS);

        IntStream.range(0, NUMBER_OF_GUESTS)
                .mapToObj(i -> new Guest(monitor, i / 2, i, NUMBER_OF_VISITS))
                .forEach(executorService::submit);

        executorService.shutdown();
    }
}
