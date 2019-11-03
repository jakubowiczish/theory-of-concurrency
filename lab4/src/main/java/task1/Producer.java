package task1;

import java.util.concurrent.locks.Condition;

public class Producer extends Thread {

    private Pipeline pipeline;

    Producer(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < pipeline.getBufferSize(); i++) {
                Condition condition = pipeline.getCondition(i);

                while (pipeline.getBufferValue(i) != -1) { // wait until consumer has consumed
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pipeline.setBufferValue(i, 0);
                pipeline.logChanges(Names.PRODUCER, i);

                condition.signal();
                pipeline.releaseLock(i);
            }
        }
    }
}
