package AuctionClass;

import com.example.javaproject.HelloApplication;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class Auction {
    private static int id_ = 0;
    private final Integer id;
    public boolean is_active;
    String title;
    int timeout; // [s]
    int currentTimeout;
    double price;
    double durationTime;
    boolean is_paused;
    SimpleStringProperty tableTitle;
    SimpleDoubleProperty tablePrice;
    SimpleIntegerProperty tableTimeout;
    SimpleStringProperty currentWinner;
    private boolean is_ended;
    public ObservableList<Pair<SimpleStringProperty, SimpleDoubleProperty>> bidders;
    String winner;
    public Auction(String title, double startingPrice, int timeout) {
        id_ += 1;
        this.id = id_;
        this.title = title;
        this.is_active = false;
        this.durationTime = timeout;
        this.timeout = timeout;
        this.currentTimeout = timeout;
        this.price = startingPrice;
        this.is_paused = false;
        tableTitle = new SimpleStringProperty(title);
        tablePrice = new SimpleDoubleProperty(startingPrice);
        currentWinner = new SimpleStringProperty("");
        tableTimeout = new SimpleIntegerProperty(timeout);
        bidders = FXCollections.observableArrayList();
    }
    public void start() {
        if(is_paused) {
            unPause();
            return;
        }
        if(is_ended) {
            return;
        }
        this.is_active = true;
        Thread timeoutDecrease = new Thread(() -> {
            while (this.currentTimeout > 0 && !this.is_ended) {
                if(!is_paused) this.currentTimeout -= 1;

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
        this.is_paused = false;
        this.is_active = true;
    }
    public void end() {
        this.is_active = false;
        this.is_ended = true;
        this.currentWinner = new SimpleStringProperty(this.winner);
        for(User user_ : HelloApplication.users) {
            if(user_.getName().equals(this.winner)) {
                user_.removeMoney(this.price);
                break;
            }
        }
    }
    public void makeBid(double price, String user) {
        if (this.is_active && this.price < price) {
            this.price = price;
            this.currentTimeout = this.timeout;

            this.winner = user;
            currentWinner = new SimpleStringProperty(user);
            SimpleDoubleProperty currentPrice = new SimpleDoubleProperty(this.price);
            Pair<SimpleStringProperty, SimpleDoubleProperty> userPair = new Pair(currentWinner, currentPrice);
            this.bidders.add(userPair);
        }
    }

    public SimpleStringProperty getTitle() {
        return tableTitle;
    }
    public SimpleDoubleProperty getPrice() {
        return new SimpleDoubleProperty(price);
    }
    public SimpleIntegerProperty getCurrentTimeout() {
        return new SimpleIntegerProperty(currentTimeout);
    }
    public SimpleStringProperty getCurrentWinner() {
        return currentWinner;
    }

    public SimpleStringProperty getCurrentState() {
        SimpleStringProperty currentState = new SimpleStringProperty("");
        if(is_ended) currentState.setValue("zakonczona");
        else if(is_active) currentState.setValue("aktywna");
        else if(is_paused) currentState.setValue("pauza");
        else currentState.setValue("waiting to start");
        return currentState;
    }

}

