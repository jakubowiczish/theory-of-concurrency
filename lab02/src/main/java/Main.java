import static task1.BinarySemaphoreExecutioner.executeCounterUsingBinarySemaphore;
import static task2.CountingSemaphoreExecutioner.executeCounterUsingCountingSemaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        executeCounterUsingBinarySemaphore();
        executeCounterUsingCountingSemaphore();
    }
}
