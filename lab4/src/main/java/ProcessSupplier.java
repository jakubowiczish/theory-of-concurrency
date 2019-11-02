import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

class ProcessSupplier {

    private List<Integer> buffer;
    private int bufferSize;

    private Lock bufferLock;
    private Map<Integer, Condition> conditionsForCell;

    private Map<Integer, Integer> indicesForProcessNumber;

    private int processingCounter;

    ProcessSupplier(List<Integer> buffer, int processingCounter) {
        this.buffer = buffer;
        this.bufferSize = buffer.size();
        this.processingCounter = processingCounter;

        this.bufferLock = new ReentrantLock();
        this.conditionsForCell = new HashMap<>();
        this.indicesForProcessNumber = new HashMap<>();

        IntStream.range(0, processingCounter + 2)
                .forEach(i -> {
                    conditionsForCell.put(i, bufferLock.newCondition());
                    indicesForProcessNumber.put(i, 0);
                });
    }

    private Runnable processBuffer(final int processNumber) {
        return () -> {
            int currentIndex = 0;

            while (getIndexOfProcess(processNumber) < bufferSize) {
                bufferLock.lock();

                try {
                    if (processNumber != 0) {
                        while (getIndexOfProcess(processNumber - 1) <= currentIndex) {
                            try {
                                conditionsForCell.get(processNumber).await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    processOneCellInBuffer(processNumber, currentIndex);
                    logProcessedCell(processNumber, currentIndex);
                    ++currentIndex;
//                    currentIndex = (currentIndex + 1) % bufferSize;
                    updateIndexOfProcess(processNumber, currentIndex);

                    if (processNumber != processingCounter + 1) {
                        conditionsForCell.get(processNumber + 1).signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bufferLock.unlock();
                }
            }
        };
    }

    Runnable getProducer() {
        return processBuffer(0);
    }

    Runnable getProcessor(int i) {
        return processBuffer(i + 1);
    }

    Runnable getConsumer() {
        return processBuffer(processingCounter + 1);
    }

    private int getIndexOfProcess(int processNumber) {
        return indicesForProcessNumber.get(processNumber);
    }

    private void updateIndexOfProcess(int processNumber, int currentIndex) {
        indicesForProcessNumber.put(processNumber, currentIndex);
    }

    private void processOneCellInBuffer(int processingIndex, int cellIndex) throws InterruptedException {
        this.buffer.set(cellIndex,
                processingIndex == processingCounter + 1
                        ? 0 : this.buffer.get(cellIndex) + 2);
    }

    private void logProcessedCell(int processNumber, int cellIndex) {
        System.out.println("Cell: " + cellIndex
                + ", processed by: " + processNumber
                + ", buffer state: " + buffer);
    }
}
