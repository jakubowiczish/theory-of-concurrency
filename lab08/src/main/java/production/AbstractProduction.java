package production;

import pararellism.MyLock;

public abstract class AbstractProduction<P> implements IProduction<P> {

    private final PThread thread = new PThread();
    private final P obj;
    private final PDrawer<P> drawer;
    private MyLock lock;
    private P result;

    public AbstractProduction(P obj, PDrawer<P> drawer) {
        this.obj = obj;
        this.drawer = drawer;
    }

    @Override
    public P getObj() {
        return this.result;
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void join() throws InterruptedException {
        thread.join();
    }

    @Override
    public void injectRefs(MyLock lock) {
        this.lock = lock;
    }

    private class PThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            result = apply(obj);
            drawer.draw(result);
        }
    }
}