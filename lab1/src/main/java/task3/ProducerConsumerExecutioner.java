package task3;

public class ProducerConsumerExecutioner {

    public static void executeProducerConsumer() {
        Buffer buffer = new Buffer();
        (new Thread(new Producer(buffer))).start();
        (new Thread(new Consumer(buffer))).start();
    }
}
