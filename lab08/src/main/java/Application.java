import pararellism.ConcurrentBlockRunner;

class Application {

    public static void main(String[] args) {
        new Executor(new ConcurrentBlockRunner()).start();
    }
}