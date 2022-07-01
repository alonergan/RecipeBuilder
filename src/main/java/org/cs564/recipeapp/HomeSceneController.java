package org.cs564.recipeapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeSceneController {

    @FXML
    private Pane browsePane;

    @FXML
    private Button browseRecipeButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Pane profilePane;

    @FXML
    private Button searchRecipeButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane settingsPane;

    public void handleClicks(ActionEvent event) {
        if (event.getSource() == homeButton) {
            profilePane.toFront();
        }
        if (event.getSource() == settingsButton) {
            settingsPane.toFront();
        }
        if (event.getSource() == browseRecipeButton) {
            browsePane.toFront();
        }
        if (event.getSource() == logoutButton) {
        }
    }

    @FXML
    void initialize() {
        assert browsePane != null : "fx:id=\"browsePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browseRecipeButton != null : "fx:id=\"browseRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchRecipeButton != null : "fx:id=\"searchRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";

    }

}
