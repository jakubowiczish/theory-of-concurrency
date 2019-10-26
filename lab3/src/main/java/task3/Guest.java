package task3;

import java.util.stream.IntStream;

public class Guest implements Runnable {

    private final WaitersMonitor monitor;
    private final int pairNumber;
    private final int guestNumber;
    private final int numberOfVisitsAtTheTable;

    public Guest(WaitersMonitor monitor, final int pairNumber, final int guestNumber, final int numberOfVisitsAtTheTable) {
        this.monitor = monitor;
        this.pairNumber = pairNumber;
        this.guestNumber = guestNumber;
        this.numberOfVisitsAtTheTable = numberOfVisitsAtTheTable;
    }

    @Override
    public void run() {
        IntStream.range(0, numberOfVisitsAtTheTable).forEach(i -> {
            try {
                monitor.book(pairNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eat();
            monitor.leave();
        });
    }

    private void eat() {
        System.out.println("Guest number: " + guestNumber + " is eating. His pair number: " + pairNumber);
    }
}
