class Buffer {

    static int AMOUNT = 100;

    private String message;

    private boolean isMessageEmpty = true;

    synchronized String take() {
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

    synchronized void put(String message) {
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
