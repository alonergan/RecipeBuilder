package org.cs564.recipeapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class MainApplication extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load login page
        Parent root = FXMLLoader.load(getClass().getResource("homeSceneController.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Recipe Builder");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// TODO: Finish home screen controllers
// TODO: Create User class to store user data, reviews, etc.
// TODO: Add and normalize data csv files