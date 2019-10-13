public class Incrementer implements Runnable {

    private BinarySemaphore binarySemaphore;

    public Incrementer(BinarySemaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Incrementing...");
            binarySemaphore.increment();
        }
    }
}
