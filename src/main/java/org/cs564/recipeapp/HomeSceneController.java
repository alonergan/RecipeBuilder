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
import javafx.stage.Stage;

public class HomeSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button browseRecipeButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchRecipeButton;

    @FXML
    private Button settingsButton;

    @FXML
    void logoutButtonClicked() throws IOException {
        Parent loginScene = FXMLLoader.load(RecipeApp.class.getResource("fxml/loginSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(loginScene, 1200, 725));
    }

    @FXML
    void searchRecipeButtonClicked() throws IOException {
        Parent findRecipeScene = FXMLLoader.load(RecipeApp.class.getResource("fxml/searchRecipeSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(findRecipeScene, 1200, 725));
    }

    @FXML
    void browseRecipeButtonClicked() throws IOException {
        Parent browseRecipesScene = FXMLLoader.load(RecipeApp.class.getResource("fxml/browseRecipeSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(browseRecipesScene, 1200, 725));
    }

    @FXML
    void settingsButtonClicked() throws IOException {
        Parent userProfileScene = FXMLLoader.load(RecipeApp.class.getResource("fxml/settingsSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(userProfileScene, 1200, 725));
    }

    @FXML
    void homeButtonClicked() throws IOException {
        Parent addRecipeScene = FXMLLoader.load(RecipeApp.class.getResource("fxml/homeSceneController.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(addRecipeScene, 1200, 725));
    }

    @FXML
    void initialize() {
        assert browseRecipeButton != null : "fx:id=\"browseRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchRecipeButton != null : "fx:id=\"searchRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";

    }

}
