package AuctionClass;

public class Auction {
    private static int id_ = 0;
    private Integer id;
    boolean is_active;
    public Product product;
    double timeout; // [s]
    double price;
    double minBid;
    double durationTime;
    boolean is_paused;
    Integer currentWinnerID;

    public Auction(Product product, double durationTime, double minBid) {
        id_ += 1;
        this.id = id_;
        this.product = product;
        this.is_active = false;
        this.timeout = durationTime;
        this.price = this.product.getPrice();
        this.minBid = minBid;
        this.durationTime = durationTime;
        this.is_paused = false;
    }
    public void start() {
        this.is_active = true;
        Thread timeoutDecrease = new Thread(() -> {
            while (this.timeout > 0 && !this.is_paused) {
                this.timeout -= 0.01;
                // Sleep for 1 second
                try {
                    Thread.sleep(10); // 1000 milliseconds = 1 second
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                    break; // Exit the loop if interrupted
                }
            }

            // Final timeout message
            if (this.timeout <= 0) {
                this.end();
            }
        });
        timeoutDecrease.start();
    }
    public void pause() {
        this.is_active = false;
        this.is_paused = true;
    }
    public void unPause() {
        this.is_active = true;
        this.is_paused = false;
    }
    public void end() {
        this.is_active = false;
    }
    public void makeBid(double price, Integer userID) {
        if (this.is_active && this.minBid < price) {
            this.price += price;
            this.timeout = this.durationTime;
            this.currentWinnerID = userID;
        }
    }
    public void showStatus() {
        String product_status = product.getStatus();
        String auction_status = "";
        auction_status += "AuctionClass.Auction ID: " + this.id.toString() + "isActive: " + this.is_active;
        System.out.println(auction_status);
        System.out.println(product_status);
    }
}

