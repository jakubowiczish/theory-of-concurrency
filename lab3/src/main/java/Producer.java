public class Producer implements Runnable {

    private final BoundedBuffer buffer;
    private final int AMOUNT;
    private final int producerId;

    public Producer(BoundedBuffer buffer, final int AMOUNT, final int producerId) {
        this.buffer = buffer;
        this.AMOUNT = AMOUNT;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        for (int i = 0; i < AMOUNT; i++) {
            try {
                buffer.put("message number: " + i + " from producer: " + producerId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}