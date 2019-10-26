package task1;

public class Consumer implements Runnable {

    private final BoundedBuffer buffer;
    private final int AMOUNT;
    private final int consumerId;

    public Consumer(BoundedBuffer buffer, final int AMOUNT, final int consumerId) {
        this.buffer = buffer;
        this.AMOUNT = AMOUNT;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        for (int i = 0; i < AMOUNT; i++) {
            String message = null;

            try {
                message = (String) buffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Consumer: " + consumerId + " "  + message);
        }
    }
}