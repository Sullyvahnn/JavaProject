package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController {
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    private ObservableList<Auction> auctions;
    public void onDeleteAuctionButtonClick(ActionEvent actionEvent) {
        SimpleStringProperty title = table.getSelectionModel().getSelectedItem().getTitle();
        for(Auction auction : auctions) {
            if(title.equals(auction.getTitle())) {
                auctions.remove(auction);
                HelloApplication.removeItem(auction);
                break;
            }
        }
        updateUI();

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