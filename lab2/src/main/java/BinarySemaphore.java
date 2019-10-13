public class BinarySemaphore {

    private static BinarySemaphore binarySemaphore;
    private static boolean isAcquired;

    private BinarySemaphore() {
    }

    public synchronized void increment() {
        while (isAcquired) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isAcquired = true;
        printAcquiredStatus();

        this.notifyAll();
    }

    public synchronized void decrement() {
        while (!isAcquired) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isAcquired = false;
        printAcquiredStatus();

        this.notifyAll();
    }

    public static BinarySemaphore getInstance() {
        if (binarySemaphore == null) {
            binarySemaphore = new BinarySemaphore();
        }

        return binarySemaphore;
    }

    private static void printAcquiredStatus() {
        System.out.println("Status: " + isAcquired);
    }
}
