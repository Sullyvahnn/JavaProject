package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SellerController{
    public TextField AuctionTitleTextField;
    public TextField StartPriceTextField;
    public TextField AuctionTimeTextField;
    public TextField timeoutTextField;
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    private ObservableList<Auction> auctions;


    public void onAuctionTitleTextField(ActionEvent actionEvent) {
    }

    public void onStartPriceTextField(ActionEvent actionEvent) {
    }

    public void onAuctionTimeTextField(ActionEvent actionEvent) {
    }

    public void onAddAuctionButtonClick(ActionEvent actionEvent) {
        String title = AuctionTitleTextField.getText();
        double price = Double.parseDouble(StartPriceTextField.getText());
        int timeout = Integer.parseInt(timeoutTextField.getText());

        Auction auction = new Auction(title, price, timeout);
        HelloApplication.addToList(auction);
        updateUI();
    }

    public void onViewBiddersButtonClick(ActionEvent actionEvent) {
    }
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentTimeout().asObject());
        licitatorsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentWinner());
        auctions = HelloApplication.getItems();
        table.setItems(auctions);
    }
    public  void updateUI() {
        table.setItems(auctions);
    }
}