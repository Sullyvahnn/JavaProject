package com.example.javaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("Zaloguj się");
        stage.setScene(scene);
        stage.show();
    }
    public static void loadBuyer(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("buyer.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML
            Scene buyerScene = new Scene(root);

            // Set the scene to the stage
            primaryStage.setScene(buyerScene);
            primaryStage.setTitle("Buyer Screen");

            // Optionally, show the stage (if switching from another stage)
            primaryStage.show();
        } catch (IOException e) {
            // Handle FXML loading errors
            System.out.println("Can't load buyer.fxml");
        }
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

    public static void main(String[] args) {
        launch();
    }
}