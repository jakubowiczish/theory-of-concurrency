class BinarySemaphore {

    private static BinarySemaphore binarySemaphore;
    private static boolean isFree = true;

    private BinarySemaphore() {
    }

    public synchronized void acquire() {
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

    public synchronized void release() {
        isFree = true;
        notifyAll();

        printAcquiredStatus();
    }

    public static BinarySemaphore getInstance() {
        if (binarySemaphore == null) {
            binarySemaphore = new BinarySemaphore();
        }

        return binarySemaphore;
    }

    private static void printAcquiredStatus() {
        System.out.println("Status: " + isFree);
    }
}
