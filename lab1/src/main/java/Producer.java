public class Producer implements Runnable {

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Buffer.AMOUNT; i++) {
            buffer.put("message " + i);
        }
    }
}