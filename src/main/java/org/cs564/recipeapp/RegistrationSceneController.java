package org.cs564.recipeapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationSceneController {

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registrationSubmitButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void initialize() {
        assert confirmPasswordTextField != null : "fx:id=\"confirmPasswordTextField\" was not injected: check your FXML file 'registrationSceneController.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'registrationSceneController.fxml'.";
        assert registrationSubmitButton != null : "fx:id=\"registrationSubmitButton\" was not injected: check your FXML file 'registrationSceneController.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'registrationSceneController.fxml'.";

    }

    @FXML
    void submitButtonClicked() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String passwordConfirmation = confirmPasswordTextField.getText();

        // Check for format issues
        if (username.isEmpty() || username.equals(" ")) {
            Alert usernameError = new Alert(Alert.AlertType.ERROR);
            usernameError.setContentText("Please enter a valid username");
            usernameError.showAndWait();
            return;
        } else if (password.length() < 8) {
            Alert passwordError = new Alert(Alert.AlertType.ERROR);
            passwordError.setContentText("Password must be at least 8 characters long");
            passwordError.showAndWait();
            return;
        } else if (!password.equals(passwordConfirmation)) {
            Alert passwordError = new Alert(Alert.AlertType.ERROR);
            passwordError.setContentText("Passwords do not match");
            passwordError.showAndWait();
            return;
        }

        // Try to add username
        if (!Users.addLogin(username, password)) {
            Alert registrationError = new Alert(Alert.AlertType.ERROR);
            registrationError.setContentText("Username already exists");
            registrationError.showAndWait();
        } else {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setContentText("User successfully registered");
            successAlert.show();

            // Switch back to login page
            Parent loginScene = FXMLLoader.load(RecipeApp.class.getResource("loginSceneController.fxml"));
            Stage window = (Stage) registrationSubmitButton.getScene().getWindow();
            window.setScene(new Scene(loginScene, 1200, 800));
        }
    }
}
