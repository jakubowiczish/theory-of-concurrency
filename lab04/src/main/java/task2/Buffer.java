package task2;

public interface Buffer {

    void put(int numToPut) throws InterruptedException;

    void take(int numToTake) throws InterruptedException;

    int getBufferSize();
}
