package pararellism;

import production.IProduction;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractBlockRunner implements BlockRunner {

    private final AbstractQueue<IProduction> list = new ConcurrentLinkedQueue<>();

    @Override
    public void startAll() {
        Iterator<IProduction> iter = list.iterator();
        while (iter.hasNext()) {
            IProduction p = iter.next();
            runOne(p);
        }

        wakeAll();
        iter = list.iterator();

        while (iter.hasNext()) {
            try {
                IProduction p = iter.next();
                p.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(AbstractBlockRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        list.clear();
    }

    @Override
    public void addThread(IProduction pThread) {
        list.add(pThread);
    }

    abstract void runOne(IProduction pOne);

    abstract void wakeAll();
}
