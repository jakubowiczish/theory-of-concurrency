package task3;

public class Buffer {

    public static int AMOUNT = 100;

    private String message;

    private boolean isMessageEmpty = true;

    public synchronized String take() {
        while (isMessageEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isMessageEmpty = true;
        notifyAll();

        return message;
    }

    public synchronized void put(String message) {
        while (!isMessageEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isMessageEmpty = false;
        this.message = message;
        notifyAll();
    }
}
