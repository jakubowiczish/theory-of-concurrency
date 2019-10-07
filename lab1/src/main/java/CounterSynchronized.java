class CounterSynchronized {

    private int counter;

    synchronized void decrement() {
        --counter;
    }

    synchronized void increment() {
        ++counter;
    }

    int getCounter() {
        return counter;
    }
}
