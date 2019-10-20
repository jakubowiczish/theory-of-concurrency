public class CountingSemaphore {

    private int counter;

    CountingSemaphore(int counter) {
        this.counter = counter;
    }

    public synchronized void acquire() {
        while (counter <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        --counter;
    }

    public synchronized void release() {
        ++counter;
        notifyAll();
    }
}
