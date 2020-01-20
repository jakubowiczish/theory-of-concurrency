package task2;

import java.util.concurrent.Semaphore;

public class BetterBuffer implements Buffer {

    private Semaphore semaphore;
    private int bufferSize;

    public BetterBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.semaphore = new Semaphore(bufferSize, true);
    }

    @Override
    public void put(int numToPut) throws InterruptedException {
        semaphore.acquire(numToPut);
        logPutting(numToPut);
    }

    @Override
    public void take(int numToTake) throws InterruptedException {
        semaphore.release(numToTake);
        logTaking(numToTake);
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    private void logPutting(int numToPut) {
        System.out.println("Better buffer put  " + numToPut);
    }

    private void logTaking(int numToTake) {
        System.out.println("Better buffer took " + numToTake);
    }
}
