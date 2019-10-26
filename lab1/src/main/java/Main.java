import static task1.CounterExecutioner.executeCounter;
import static task2.SynchronizedCounterExecutioner.executeCounterSynchronized;
import static task3.ProducerConsumerExecutioner.executeProducerConsumer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        executeCounter();
        executeCounterSynchronized();
        executeProducerConsumer();
    }
}
