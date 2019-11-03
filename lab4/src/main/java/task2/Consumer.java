package task2;

import java.util.Random;

public class Consumer extends Thread {

    private Buffer buffer;
    private int maxSizeToConsume;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
        this.maxSizeToConsume = buffer.getBufferSize() / 2;
    }

    @Override
    public void run() {
        while (true) {
            int randomValue = new Random().nextInt(maxSizeToConsume);

            try {
                buffer.take(randomValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
