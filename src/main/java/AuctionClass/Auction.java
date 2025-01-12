package AuctionClass;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Auction {
    private static int id_ = 0;
    private final Integer id;
    boolean is_active;
    String title;
    int timeout; // [s]
    int currentTimeout;
    double price;
    double minBid;
    double durationTime;
    boolean is_paused;
    Integer currentWinnerID;
    SimpleStringProperty tableTitle;
    SimpleDoubleProperty tablePrice;
    SimpleIntegerProperty tableTimeout;
    SimpleStringProperty currentWinner;

    public Auction(String title, double startingPrice, int timeout) {
        id_ += 1;
        this.id = id_;
        this.title = title;
        this.is_active = false;
        this.durationTime = timeout;
        this.timeout = timeout;
        this.currentTimeout = timeout;
        this.price = startingPrice;
        this.minBid = (int)(startingPrice * 0.1);
        this.is_paused = false;
        tableTitle = new SimpleStringProperty(title);
        tablePrice = new SimpleDoubleProperty(startingPrice);
        currentWinner = new SimpleStringProperty("");
        tableTimeout = new SimpleIntegerProperty(timeout);
    }
    public void start() {
        this.is_active = true;
        Thread timeoutDecrease = new Thread(() -> {
            while (this.currentTimeout > 0 && !this.is_paused) {
                this.currentTimeout -= 1;
                // Sleep for 1 second
                try {
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                    break; // Exit the loop if interrupted
                }
            }

            // Final timeout message
            if (this.currentTimeout <= 0) {
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
    public void makeBid(double price, String user) {
        if (this.is_active && this.minBid < price) {
            this.price += price;
            this.currentTimeout = this.timeout;
            this.currentWinnerID = 1;
            currentWinner = new SimpleStringProperty(user);
        }
    }

    public SimpleStringProperty getTitle() {
        return tableTitle;
    }
    public SimpleDoubleProperty getPrice() {
        return tablePrice;
    }
    public SimpleIntegerProperty getCurrentTimeout() {
        return tableTimeout;
    }
    public SimpleStringProperty getCurrentWinner() {
        return currentWinner;
    }

}

