public class Client implements Runnable {

    private Shop shop;
    private int id;

    Client(Shop shop, int id) {
        this.shop = shop;
        this.id = id;
    }

    @Override
    public void run() {
        shop.getBasket(id);

        try {
            System.out.println("Client: " + id + " is doing the shopping");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shop.returnBasket(id);
    }
}
