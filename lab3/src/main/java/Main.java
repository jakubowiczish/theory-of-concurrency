import task1.FirstTaskExecutioner;
import task2.SecondTaskExecutioner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FirstTaskExecutioner.executeBoundedBufferOnProducersAndConsumers();
        SecondTaskExecutioner.executePrintersMonitor();
    }
}
