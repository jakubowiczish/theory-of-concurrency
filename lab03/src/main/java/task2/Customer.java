package task2;

import java.util.Random;

public class Customer implements Runnable {

    private int customerId;
    private PrintersMonitor printersMonitor;

    public Customer(int customerId, PrintersMonitor printersMonitor) {
        this.customerId = customerId;
        this.printersMonitor = printersMonitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int printerId = printersMonitor.book();
                System.out.println(customerId + " has started printing");
                Thread.sleep(new Random().nextInt(1000) + 100);
                System.out.println(customerId + " has ended printing");
                printersMonitor.release(printerId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
