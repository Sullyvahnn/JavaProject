package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;

public class BiddersController {
    public TableView<Pair<SimpleStringProperty, SimpleDoubleProperty>> table;
    public TableColumn<Pair<SimpleStringProperty, SimpleDoubleProperty>, String> UserNameColumn;
    public TableColumn<Pair<SimpleStringProperty, SimpleDoubleProperty>, Double> PriceColumn;
    private Auction auction;
    public void start(Auction auction) {
        int idx = HelloApplication.auctions.indexOf(auction);
        if(idx==-1) {
            HelloApplication.createAlert("nie wybrano aukcji");
            return;
        }
        UserNameColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        PriceColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().asObject());
        this.auction = HelloApplication.auctions.get(idx);
        ObservableList<Pair<SimpleStringProperty,SimpleDoubleProperty>> bidders = this.auction.bidders;

        table.getItems().clear();
        table.setItems(this.auction.bidders);
    }
}
