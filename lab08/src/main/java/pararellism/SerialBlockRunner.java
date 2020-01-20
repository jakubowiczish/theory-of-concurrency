package pararellism;

import production.IProduction;

public class SerialBlockRunner extends AbstractBlockRunner {

    private final MyLock lock = new MyLock();

    @Override
    void runOne(IProduction pOne) {
        pOne.injectRefs(lock);
        pOne.start();
        lock.unlock();
    }

    @Override
    void wakeAll() {
    }
}
