package org.cs564.recipeapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private TextField passwordTextField;

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
    void loginSubmitButtonClicked() {
        String usernameText = usernameTextField.getCharacters().toString();
        String passwordText = usernameTextField.getCharacters().toString();

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
        boolean loginCorrect = Users.verifyLogin(usernameText, passwordText);
        if (!loginCorrect) {
            Alert loginError = new Alert(Alert.AlertType.ERROR);
            loginError.setContentText("Username and password invalid\nPlease try again");
            loginError.showAndWait();
        } else {
            // Handle scene change and login information
        }

    }

    void loginNewUserButtonClicked() {
        // Change scene or create popup window for registration
        // TODO
    }
}
