package task2;

import java.util.Random;

public class Producer extends Thread {

    private Buffer buffer;
    private int maxSizeToProduce;

    Producer(Buffer buffer) {
        this.buffer = buffer;
        this.maxSizeToProduce = buffer.getBufferSize() / 2;
    }

    @Override
    public void run() {
        while (true) {
            int randomValue = new Random().nextInt(maxSizeToProduce);

            try {
                buffer.put(randomValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
