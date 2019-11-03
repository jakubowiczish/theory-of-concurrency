package task1;

import java.util.concurrent.locks.Condition;

public class Consumer extends Thread {

    private Pipeline pipeline;
    private int numberOfProcessesProcessing;

    Consumer(Pipeline pipeline, int numberOfProcessesProcessing) {
        this.pipeline = pipeline;
        this.numberOfProcessesProcessing = numberOfProcessesProcessing;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < pipeline.getBufferSize(); i++) {
                Condition condition = pipeline.getCondition(i);

                while (pipeline.getBufferValue(i) != numberOfProcessesProcessing) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pipeline.setBufferValue(i, -1);
                pipeline.logChanges(Names.CONSUMER, i);

                condition.signal();
                pipeline.releaseLock(i);
            }
        }
    }
}
