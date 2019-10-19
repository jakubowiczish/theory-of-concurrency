class CountingSemaphore {

    private int counter;

    CountingSemaphore(int counter) {
        this.counter = counter;
    }

    synchronized void acquire() {
        while (counter <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        --counter;
    }

    synchronized void release() {
        ++counter;
        notifyAll();
    }
}
