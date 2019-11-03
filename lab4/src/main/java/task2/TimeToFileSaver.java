package task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TimeToFileSaver {

    private static int[] cOccurrences;
    private static long[] cTimeSum;

    private static int[] pOccurrences;
    private static long[] pTimeSum;

    private static String consumerFileName;
    private static String producerFileName;

    private static void initialize(int bufferSize) {
        cOccurrences = new int[bufferSize + 1];
        cTimeSum = new long[bufferSize + 1];

        pOccurrences = new int[bufferSize + 1];
        pTimeSum = new long[bufferSize + 1];
    }

    static void addProducerTime(int number, long time) {
        pOccurrences[number]++;
        pTimeSum[number] += time;
    }

    static void addConsumerTime(int number, long time) {
        cOccurrences[number]++;
        cTimeSum[number] += time;
    }

    private static void saveConsumerResultsToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(consumerFileName)));

        for (int i = 0; i < cOccurrences.length; i++) {
            if (cOccurrences[i] == 0) continue;

            long consumerAverageTimeValue = cTimeSum[i] / cOccurrences[i];
            String line = i + ";" + consumerAverageTimeValue + "\n";
            writer.write(line);
        }

        writer.close();
    }

    private static void saveProducerResultsToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(producerFileName)));

        for (int i = 0; i < pOccurrences.length; i++) {
            if (pOccurrences[i] == 0) continue;

            long producerAverageTimeValue = pTimeSum[i] / pOccurrences[i];
            String line = i + ";" + producerAverageTimeValue + "\n";
            writer.write(line);
        }

        writer.close();
    }

    private static void setProducerFileName(String producerFileName) {
        TimeToFileSaver.producerFileName = producerFileName;
    }

    private static void setConsumerFileName(String consumerFileName) {
        TimeToFileSaver.consumerFileName = consumerFileName;
    }

    static void initializeDataCollecting(int bufferSize, int numberOfProducersConsumers, boolean isNaive) {
        initialize(bufferSize);

        String prefix = isNaive ? "./Naive" : "./Better";

        setConsumerFileName(prefix + "_Consumer_M" + bufferSize + "N" + numberOfProducersConsumers + ".txt");
        setProducerFileName(prefix + "_Producer_M" + bufferSize + "N" + numberOfProducersConsumers + ".txt");
    }

    static void saveCollectedData() {
        try {
            saveConsumerResultsToFile();
            saveProducerResultsToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
