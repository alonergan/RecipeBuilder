package org.cs564.recipeapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newUserButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button quitButton;

    @FXML
    void initialize() {
        assert newUserButton != null : "fx:id=\"newUserButton\" was not injected: check your FXML file 'loginSceneController_modern.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'loginSceneController_modern.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'loginSceneController_modern.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'loginSceneController_modern.fxml'.";

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
            Parent homeScene = FXMLLoader.load(MainApplication.class.getResource("homeSceneController.fxml"));
            homeScene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            Stage window = (Stage) signInButton.getScene().getWindow();
            window.setScene(new Scene(homeScene, 1200, 725));
        }

    }
    @FXML
    void loginNewUserButtonClicked() throws IOException {
        // Change scene or create popup window for registration
        Parent registrationScene = FXMLLoader.load(MainApplication.class.getResource("registrationSceneController.fxml"));
        Stage window = (Stage) newUserButton.getScene().getWindow();
        window.setScene(new Scene(registrationScene, 1200, 725));
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
    }
}

