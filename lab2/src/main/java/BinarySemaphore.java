class BinarySemaphore {

    private static BinarySemaphore binarySemaphore;
    private static boolean isFree = true;

    private BinarySemaphore() {
    }

    synchronized void acquire() {
        while (!isFree) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isFree = false;
        printAcquiredStatus();
    }

    synchronized void release() {
        isFree = true;
        notifyAll();

        printAcquiredStatus();
    }

    static BinarySemaphore getInstance() {
        if (binarySemaphore == null) {
            binarySemaphore = new BinarySemaphore();
        }

        return binarySemaphore;
    }

    private static void printAcquiredStatus() {
        System.out.println("Status: " + isFree);
    }
}
