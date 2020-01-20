package task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NaiveBuffer implements Buffer {

    private int bufferSize;
    private int numOfPlacesTaken;
    private int numOfPlacesAvailable;

    private Lock lock;
    private Condition condition;

    NaiveBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.numOfPlacesTaken = 0;
        this.numOfPlacesAvailable = bufferSize;

        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    @Override
    public void put(int numToPut) throws InterruptedException {
        lock.lock();

        try {
            while (numOfPlacesAvailable < numToPut) {
                condition.await();
            }

            logPutting(numToPut);

            numOfPlacesAvailable -= numToPut;
            numOfPlacesTaken += numToPut;

            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void take(int numToTake) throws InterruptedException {
        lock.lock();

        try {
            while (numOfPlacesTaken < numToTake) {
                condition.await();
            }

            logTaking(numToTake);

            numOfPlacesTaken -= numToTake;
            numOfPlacesAvailable += numToTake;

            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    private void logPutting(int numToPut) {
        System.out.println("Naive buffer put  " + numToPut);
        logAvailableAndTaken();
    }

    private void logTaking(int numToTake) {
        System.out.println("Naive buffer took " + numToTake);
        logAvailableAndTaken();
    }

    private void logAvailableAndTaken() {
        System.out.println("Available: " + numOfPlacesAvailable + ", taken :" + numOfPlacesTaken);
    }
}
