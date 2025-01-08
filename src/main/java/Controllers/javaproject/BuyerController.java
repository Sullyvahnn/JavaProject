package Controllers.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BuyerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onOfferedAmountTextField(ActionEvent actionEvent) {
    }

    public void onOfferButtonClick(ActionEvent actionEvent) {
    }

    public void onRefreshBidButtonClick(ActionEvent actionEvent) {
    }
}