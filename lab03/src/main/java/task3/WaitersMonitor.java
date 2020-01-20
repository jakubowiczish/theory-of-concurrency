package task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class WaitersMonitor {

    private final Lock lock;

    private final int numberOfPairs;

    private final Map<Integer, Condition> pairReadyConditions;

    private final Set<Integer> waitingForPair;

    private final Condition tableEmptyCondition;
    private int numberOfGuestsAtTable = 0;

    public WaitersMonitor(final int numberOfPairs) {
        this.numberOfPairs = numberOfPairs;
        this.lock = new ReentrantLock();
        this.waitingForPair = new HashSet<>();
        this.tableEmptyCondition = lock.newCondition();

        this.pairReadyConditions = new HashMap<>();
        IntStream.range(0, numberOfPairs)
                .forEach(i -> pairReadyConditions.put(i, lock.newCondition()));
    }

    public void book(int pairNumber) throws InterruptedException {
        if (pairNumber > numberOfPairs)
            throw new IllegalArgumentException("Podany numer pary jest błędny");

        lock.lock();

        try {
            if (!waitingForPair.contains(pairNumber)) {
                waitingForPair.add(pairNumber);
                pairReadyConditions.get(pairNumber).await();
            } else {
                while (numberOfGuestsAtTable > 0)
                    tableEmptyCondition.await();

                numberOfGuestsAtTable = 2;
                waitingForPair.remove(pairNumber);
                pairReadyConditions.get(pairNumber).signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void leave() {
        lock.lock();

        try {
            --numberOfGuestsAtTable;
            if (numberOfGuestsAtTable == 0)
                tableEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
