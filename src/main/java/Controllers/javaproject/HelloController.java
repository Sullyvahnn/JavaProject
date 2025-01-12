package Controllers.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onBuyerButtonClick(ActionEvent actionEvent) {
    }

    public void onSellerButtonClick(ActionEvent actionEvent) {
    }

    public void onAdminButtonClick(ActionEvent actionEvent) {

    }
}