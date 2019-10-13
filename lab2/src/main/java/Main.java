public class Main {

    public static void main(String[] args) {
        Thread incrementer = new Thread(new Incrementer(BinarySemaphore.getInstance()));
        Thread decrementer = new Thread(new Decrementer(BinarySemaphore.getInstance()));

        incrementer.start();
        decrementer.start();
    }
}
