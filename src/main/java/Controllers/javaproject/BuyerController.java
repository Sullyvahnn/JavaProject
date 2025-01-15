package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class BuyerController{
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    public TextField OfferedAmountTextField;
    private ObservableList<Auction> auctions;
    public void onOfferedAmountTextField(ActionEvent actionEvent) {
    }

    public void onOfferButtonClick(ActionEvent actionEvent) {
        int row = table.getSelectionModel().getSelectedIndex();
        double value = Double.parseDouble(OfferedAmountTextField.getText());
        HelloApplication.auctions.get(row).makeBid(value,"user");

    }
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentTimeout().asObject());
        licitatorsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentWinner());
        auctions = HelloApplication.getItems();
        table.setItems(auctions);
        startRefreshing();
    }
    public  void updateUI() {
        table.setItems(auctions);
        table.refresh();
    }
    Thread refreshThread = new Thread(() -> {
        while (true) {
            try {
                // Simulate background task
                Thread.sleep(16);  // Refresh every 5 seconds
                Platform.runLater(this::updateUI);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    public void startRefreshing() {
        refreshThread.start();
    }
}