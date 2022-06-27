package org.cs564.recipeapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addRecipesButton;

    @FXML
    private Button browseRecipesButton;

    @FXML
    private Button findRecipeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button userProfileButton;

    @FXML
    void initialize() {
        assert addRecipesButton != null : "fx:id=\"addRecipesButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browseRecipesButton != null : "fx:id=\"browseRecipesButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert findRecipeButton != null : "fx:id=\"findRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert userProfileButton != null : "fx:id=\"userProfileButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";

    }

    @FXML
    void handleLogoutButtonClicked() throws IOException {
        Parent loginScene = FXMLLoader.load(RecipeApp.class.getResource("loginSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(loginScene, 1200, 800));
    }
}
