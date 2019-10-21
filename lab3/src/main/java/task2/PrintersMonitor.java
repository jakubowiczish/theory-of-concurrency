package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PrintersMonitor {

    private List<Boolean> printersAvailability;

    private Lock lock = new ReentrantLock();
    private Condition isAnyPrinterAvailable = lock.newCondition();

    public PrintersMonitor(int numberOfPrinters) {
        this.printersAvailability = new ArrayList<>(numberOfPrinters);
        IntStream
                .range(0, numberOfPrinters)
                .forEach(i -> printersAvailability.add(true));
    }

    public int book() throws InterruptedException {
        int freePrinterIndex = -1;
        lock.lock();

        while (true) {
            for (int i = 0; i < printersAvailability.size(); i++) {
                if (printersAvailability.get(i)) {
                    freePrinterIndex = i;
                    printersAvailability.set(i, false);
                    break;
                }
            }

            if (freePrinterIndex == -1) {
                isAnyPrinterAvailable.await();
            } else break;
        }

        lock.unlock();
        return freePrinterIndex;
    }

    public void release(int printerNumber) {
        lock.lock();
        printersAvailability.set(printerNumber, true);
        isAnyPrinterAvailable.signal();
        lock.unlock();
    }
}
