package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SellerController {
    public TextField AuctionTitleTextField;
    public TextField StartPriceTextField;
    public TextField AuctionTimeTextField;
    public TextField timeoutTextField;
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    public TableColumn<Auction, String> isActiveColumn;


    public void onAuctionTitleTextField(ActionEvent actionEvent) {
    }

    public void onStartPriceTextField(ActionEvent actionEvent) {
    }


    public void onAddAuctionButtonClick(ActionEvent actionEvent) {
        String title = AuctionTitleTextField.getText();
        double price = Double.parseDouble(StartPriceTextField.getText());
        int timeout = Integer.parseInt(timeoutTextField.getText());

        Auction auction = new Auction(0, title, price, timeout);
        HelloApplication.addToList(auction);
        updateUI();
    }

    public void onViewBiddersButtonClick(ActionEvent actionEvent) {
        Auction auction = table.getSelectionModel().getSelectedItem();
        if(auction == null) {
            HelloApplication.createAlert("Nie wybrano aukcji");
            return;
        }
        showPoppingScreen(auction,1,"bidders.fxml");
    }
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentTimeout().asObject());
        licitatorsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentWinner());
        isActiveColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentState());
        startRefreshing();
    }
    public void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            TablePosition<?, ?> pos = table.getSelectionModel().getSelectedCells().getFirst();
            int row = pos.getRow();
            int col = pos.getColumn();
            Auction selectedAuction = table.getItems().get(row);
            if (col == 4) {
                showPoppingScreen(selectedAuction, row, "is_active.fxml");
            }

            if (col == 0)  showPoppingScreen(selectedAuction,row, "is_active.fxml");
        }
    }
    public void updateUI() {
        Platform.runLater(() -> {
            table.setItems(HelloApplication.auctions);
            table.refresh();
        });

    }
    public void showPoppingScreen(Auction auction,int row, String fxmFile) {
        try {
            // Load FXML for the popup
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmFile));
            AnchorPane root = loader.load();
            if("is_active.fxml".equals(fxmFile)) {
                ChangeActiveStateController changeActiveStateController = loader.getController();
                changeActiveStateController.start(row);
                Stage popupStage = new Stage();
                popupStage.setTitle("Popup Window");
                popupStage.setScene(new Scene(root, 400, 200));
                popupStage.show();
            }
            if("bidders.fxml".equals(fxmFile)) {
                BiddersController changeActiveStateController = loader.getController();
                changeActiveStateController.start(auction);
                Stage popupStage = new Stage();
                popupStage.setTitle("Popup Window");
                popupStage.setScene(new Scene(root, 380, 540));
                popupStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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