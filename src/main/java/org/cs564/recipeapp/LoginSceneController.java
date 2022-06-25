package org.cs564.recipeapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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

}
