package Controllers.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SellerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onAuctionTitleTextField(ActionEvent actionEvent) {
    }

    public void onStartPriceTextField(ActionEvent actionEvent) {
    }

    public void onAuctionTimeTextField(ActionEvent actionEvent) {
    }

    public void onAddAuctionButtonClick(ActionEvent actionEvent) {
    }

    public void onViewBiddersButtonClick(ActionEvent actionEvent) {
    }
}