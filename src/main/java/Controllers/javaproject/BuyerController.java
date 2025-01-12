package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BuyerController{
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    private ObservableList<Auction> auctions;
    public void onOfferedAmountTextField(ActionEvent actionEvent) {
    }

    public void onOfferButtonClick(ActionEvent actionEvent) {
    }

    public void onRefreshBidButtonClick(ActionEvent actionEvent) {
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