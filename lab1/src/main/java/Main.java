public class Main {

    public static final int COUNTER = 100000;

    public static void main(String[] args) throws InterruptedException {
        CounterSynchronized counterSynchronized = new CounterSynchronized();

        Thread first = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counterSynchronized.increment();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < COUNTER; i++) {
                counterSynchronized.decrement();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(counterSynchronized.getCounter());
    }
}
