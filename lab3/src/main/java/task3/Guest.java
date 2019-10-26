package task3;

public class Guest implements Runnable {

    private final WaitersMonitor monitor;
    private final int pairNumber;
    private final int guestNumber;

    public Guest(WaitersMonitor monitor, final int pairNumber, final int guestNumber) {
        this.monitor = monitor;
        this.pairNumber = pairNumber;
        this.guestNumber = guestNumber;
    }

    @Override
    public void run() {

        try {
            monitor.book(pairNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        eat();
        monitor.leave();
    }

    private void eat() {
        System.out.println("Guest number: " + guestNumber + " is eating. His pair number: " + pairNumber);
    }
}
