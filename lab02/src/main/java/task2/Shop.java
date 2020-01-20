package task2;

public class Shop {

    private CountingSemaphore countingSemaphore;
    private int baskets;

    public Shop(int baskets) {
        this.baskets = baskets;
        countingSemaphore = new CountingSemaphore(baskets);
    }

    public void returnBasket(int clientId) {
        System.out.println("Client: " + clientId + " returned basket");
        ++baskets;
        countingSemaphore.release();
    }

    public void getBasket(int clientId) {
        countingSemaphore.acquire();
        System.out.println("Client: " + clientId + " took basket");
        --baskets;
    }

    public int getBaskets() {
        return baskets;
    }
}
