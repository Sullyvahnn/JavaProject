package Controllers.javaproject;

import AuctionClass.Auction;
import com.example.javaproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChangeActiveStateController {
    public Button activeButton;
    public Button aouseButton;
    public Button waitButton;
    private int row;
    public void start(int row) {
        this.row = row;
    }
    public void onActiveButtonClick(ActionEvent actionEvent) {
        HelloApplication.auctions.get(row).start();
        close();

    }

    public void onPouseButtonClick(ActionEvent actionEvent) {
        HelloApplication.auctions.get(row).pause();
        close();
    }

    public void onWaitButtonClick(ActionEvent actionEvent) {
        HelloApplication.auctions.get(row).end();
        close();
    }
    private void close() {
        Stage stage = (Stage) waitButton.getScene().getWindow();

        if (stage != null) {
            stage.close(); // Close the popup window
        }

    }
}
