package task1;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

class Pipeline {

    private int[] buffer;
    private Lock[] locks;
    private Condition[] conditions;

    Pipeline(int bufferSize) {
        buffer = new int[bufferSize];
        locks = new Lock[bufferSize];
        conditions = new Condition[bufferSize];

        IntStream.range(0, bufferSize)
                .forEach(i -> {
                    buffer[i] = -1;
                    locks[i] = new ReentrantLock();
                    conditions[i] = locks[i].newCondition();
                });
    }

    Condition getCondition(int i) {
        locks[i].lock();
        return conditions[i];
    }

    void releaseLock(int i) {
        locks[i].unlock();
    }

    int getBufferValue(int i) {
        return buffer[i];
    }

    void setBufferValue(int i, int value) {
        buffer[i] = value;
    }

    int getBufferSize() {
        return buffer.length;
    }

    void logProcessingChanges(Names processName, int cellIndex, int id) {
        System.out.println(
                Arrays.toString(buffer)
                        + "   " + processName.toString() + " " + id
                        + " on cell: " + cellIndex);

        sleep(1000);
    }

    void logChanges(Names processName, int cellIndex) {
        System.out.println(
                Arrays.toString(buffer)
                        + "   " + processName.toString()
                        + " on cell: " + cellIndex);

        sleep(1000);
    }

    private void sleep(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
