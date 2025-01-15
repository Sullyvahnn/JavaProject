package Controllers.javaproject;

import AuctionClass.Auction;
import AuctionClass.DatabaseUpdater;
import AuctionClass.User;
import com.example.javaproject.HelloApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class BuyerController{
    public TableColumn<Auction, String> titleColumn;
    public TableColumn<Auction, Double> priceColumn;
    public TableColumn<Auction, Integer> timeColumn;
    public TableColumn<Auction, String> licitatorsNumberColumn;
    public TableView<Auction> table;
    public TextField OfferedAmountTextField;
    public TextField PaymentAmountTextField;
    public TextField BalanceTextField;
    public SplitMenuButton currentUserTextBox;
    public TextField accountStatus;
    private ObservableList<Auction> auctions;
    private ObservableList<Auction> activeAuctions;
    public ObservableList<User> users = FXCollections.observableArrayList();
    User currentUser;
    int currentUserID;
    public void onOfferedAmountTextField(ActionEvent actionEvent) {
    }

    public void onOfferButtonClick(ActionEvent actionEvent) {
        Auction auction = table.getSelectionModel().getSelectedItem();
        if(OfferedAmountTextField.getText().isEmpty()) {
            HelloApplication.createAlert("Wpisz kwote licytacji");
            return;
        }
            double value = Double.parseDouble(OfferedAmountTextField.getText());

        if(currentUser.getMoney()>=value) {
            int idx = HelloApplication.auctions.indexOf(auction);
            if(idx==-1) {
                HelloApplication.createAlert("wybierz aukcje");
                return;
            }
            HelloApplication.auctions.get(idx).makeBid(value,currentUser);

        } else HelloApplication.createAlert("Za malo pieniedzy");

    }
    public void initialize() {
        DatabaseUpdater.establishConnection();
        users = DatabaseUpdater.getUsersData();
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentTimeout().asObject());
        licitatorsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentWinner());
        auctions = HelloApplication.getItems();
        activeAuctions = FXCollections.observableArrayList();
        currentUser = users.getFirst();
        currentUserID = currentUser.getId();
        currentUserTextBox.setText(currentUser.getName());
        for(Auction auction : auctions) {
            if(auction.is_active) {
                activeAuctions.add(auction);
            }
        }
        table.setItems(activeAuctions);
        for (User user : users) {
            MenuItem menuItem = new MenuItem(user.getName());
            menuItem.setOnAction(this::onMenuItem);
            menuItem.setId(user.getName());
            currentUserTextBox.getItems().add(menuItem);
        }
        startRefreshing();
    }
    public  void updateUI() {
        users = DatabaseUpdater.getUsersData();
        currentUser = users.get(currentUserID-1);
        table.setItems(activeAuctions);
        BalanceTextField.setText(String.valueOf(currentUser.getMoney()));
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
    public void onMenuItem(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source instanceof MenuItem menuItem) {
            String id = menuItem.getId();
            for (User user : users) {
                if (Objects.equals(user.getName(), id)) {
                    currentUser = user;
                    currentUserID = currentUser.getId();
                    currentUserTextBox.setText(currentUser.getName());
                }
            }

        }
//
    }

    public void onDeposit(ActionEvent actionEvent) {
        try {
            double depositAmount = Double.parseDouble(PaymentAmountTextField.getText());
            int idx = currentUser.getId();
            users.get(idx-1).addMoney(depositAmount);
            updateUI();
        } catch (NumberFormatException e) {
            HelloApplication.createAlert("Nieprawidłowa kwota wpłaty");
        }
    }

}