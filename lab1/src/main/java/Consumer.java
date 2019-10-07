public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Buffer.AMOUNT; i++) {
            String message = buffer.take();
            System.out.println("Message: " + message);
        }
    }
}