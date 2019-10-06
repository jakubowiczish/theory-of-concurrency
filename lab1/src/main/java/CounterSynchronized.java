public class CounterSynchronized {

    private int counter;

    public synchronized void decrement() {
        --counter;
    }

    public synchronized void increment() {
        ++counter;
    }

    public int getCounter() {
        return counter;
    }
}
