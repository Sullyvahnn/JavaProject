package com.example.javaproject;

import AuctionClass.Auction;
import Controllers.javaproject.ChangeActiveStateController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {
    public static ObservableList<Auction> auctions = FXCollections.observableArrayList();
    public static void addToList(Auction auction) {
        auctions.add(auction);
    }
    @Override
    public void start(Stage stage) throws IOException {
        simulateAuctions();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("Zaloguj się");
        stage.setScene(scene);
        stage.show();
    }
    public static void load(Stage primaryStage, String FXML_name) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(FXML_name));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML
            Scene buyerScene = new Scene(root);
            // Set the scene to the stage
            primaryStage.setScene(buyerScene);
            primaryStage.setTitle("Main Application");
            primaryStage.setOnCloseRequest(event -> {
            if(!FXML_name.equals("hello-view.fxml")) {
                // Intercept the close request
                event.consume(); // Prevent the application from closing

                // Load the login screen instead
                load(primaryStage, "hello-view.fxml");
            }
            });

            // Optionally, show the stage (if switching from another stage)
            primaryStage.show();
        } catch (IOException e) {
            // Handle FXML loading errors
            System.out.println("Can't load fxml");
        }
    }

    public static ObservableList<Auction> getItems() {
        return auctions;
    }
    public static void removeItem(Auction auction) {
        auctions.remove(auction);
    }
    public static void simulateAuctions() {
        auctions.addAll(
                new Auction("zapalki", 9.99, 10),
                new Auction("fotel", 200, 60),
                new Auction("telewizor", 3000, 180)
        );
    }

    public static void main(String[] args) {
        launch();
    }
}