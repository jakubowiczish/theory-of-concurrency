package production;

import pararellism.MyLock;

public interface IProduction<P> {

    P apply(P p);

    void join() throws InterruptedException;

    void start();

    void injectRefs(MyLock lock);

    P getObj();
}
