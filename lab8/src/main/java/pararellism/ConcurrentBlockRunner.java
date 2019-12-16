package pararellism;

import production.IProduction;

public class ConcurrentBlockRunner extends AbstractBlockRunner {

    private final MyLock lock = new MyLock();

    @Override
    void runOne(IProduction pOne) {
        pOne.injectRefs(lock);
        pOne.start();
    }

    @Override
    void wakeAll() {
        lock.unlock();
    }

}
