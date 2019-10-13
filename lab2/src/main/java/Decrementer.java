public class Decrementer implements Runnable {

    private BinarySemaphore binarySemaphore;

    public Decrementer(BinarySemaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Decrementing...");
            binarySemaphore.decrement();
        }
    }
}
