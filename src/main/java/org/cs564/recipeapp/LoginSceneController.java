package org.cs564.recipeapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginNewUserButton;

    @FXML
    private Label loginPaneLabel;

    @FXML
    private Button loginSubmitButton;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane mainLoginPane;

    @FXML
    private Label mainTitle;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void initialize() {
        assert loginNewUserButton != null : "fx:id=\"loginNewUserButton\" was not injected: check your FXML file 'Untitled'.";
        assert loginPaneLabel != null : "fx:id=\"loginPaneLabel\" was not injected: check your FXML file 'Untitled'.";
        assert loginSubmitButton != null : "fx:id=\"loginSubmitButton\" was not injected: check your FXML file 'Untitled'.";
        assert mainAnchorPane != null : "fx:id=\"mainAnchorPane\" was not injected: check your FXML file 'Untitled'.";
        assert mainLoginPane != null : "fx:id=\"mainLoginPane\" was not injected: check your FXML file 'Untitled'.";
        assert mainTitle != null : "fx:id=\"mainTitle\" was not injected: check your FXML file 'Untitled'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'Untitled'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'Untitled'.";

    }

    @FXML
    void loginSubmitButtonClicked() throws IOException {
        String usernameText = usernameTextField.getText();
        String passwordText = passwordTextField.getText();

        // Check for text entries
        if (usernameText.isEmpty()) {
            Alert usernameError = new Alert(Alert.AlertType.ERROR);
            usernameError.setContentText("You must enter a valid username");
            usernameError.showAndWait();
            return;
        } else if (passwordText.length() < 8) {
            Alert passwordError = new Alert(Alert.AlertType.ERROR);
            passwordError.setContentText("Password must be at least 8 characters long");
            passwordError.showAndWait();
            return;
        }

        // Handle login

        if (!Users.verifyLogin(usernameText, passwordText)) {
            Alert loginError = new Alert(Alert.AlertType.ERROR);
            loginError.setContentText("Username and/or password invalid\nPlease try again");
            loginError.showAndWait();
        } else {
            // Handle scene change and login information
            Parent homeScene = FXMLLoader.load(RecipeApp.class.getResource("homeSceneController.fxml"));
            Stage window = (Stage) loginSubmitButton.getScene().getWindow();
            window.setScene(new Scene(homeScene, 1200, 800));
        }

    }
    @FXML
    void loginNewUserButtonClicked() throws IOException {
        // Change scene or create popup window for registration
        Parent registrationScene = FXMLLoader.load(RecipeApp.class.getResource("registrationSceneController.fxml"));
        Stage window = (Stage) loginNewUserButton.getScene().getWindow();
        window.setScene(new Scene(registrationScene, 600, 400));
    }
}

