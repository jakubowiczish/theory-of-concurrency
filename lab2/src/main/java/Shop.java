class Shop {

    private CountingSemaphore countingSemaphore;
    private int baskets;

    Shop(int baskets) {
        this.baskets = baskets;
        countingSemaphore = new CountingSemaphore(baskets);
    }

    void returnBasket(int clientId) {
        System.out.println("Client: " + clientId + " returns basket");
        ++baskets;
        countingSemaphore.release();
    }

    void getBasket(int clientId) {
        countingSemaphore.acquire();
        System.out.println("Client: " + clientId + " takes basket");
        --baskets;
    }

    int getBaskets() {
        return baskets;
    }
}
