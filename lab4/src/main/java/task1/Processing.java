package task1;

import java.util.concurrent.locks.Condition;

public class Processing extends Thread {

    private Pipeline pipeline;
    private int id;

    Processing(Pipeline pipeline, int id) {
        this.pipeline = pipeline;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < pipeline.getBufferSize(); i++) {
                Condition condition = pipeline.getCondition(i);

                while (pipeline.getBufferValue(i) != id - 1) { // previous processing guy
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pipeline.setBufferValue(i, id);
                pipeline.logProcessingChanges(Names.PROCESSING, i, id);

                condition.signal();
                pipeline.releaseLock(i);
            }
        }
    }
}
