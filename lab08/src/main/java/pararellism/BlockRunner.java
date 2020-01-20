package pararellism;

import production.IProduction;

public interface BlockRunner {

    void startAll();

    void addThread(IProduction pThread);
}
