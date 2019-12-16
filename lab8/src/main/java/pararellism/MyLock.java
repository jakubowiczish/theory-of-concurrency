package pararellism;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLock {

    private boolean blocked = false;

    public synchronized void lock() {
        blocked = true;
    }

    public synchronized void unlock() {
        blocked = false;
        notifyAll();
    }

    public synchronized void pass() {
        while (blocked) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MyLock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
