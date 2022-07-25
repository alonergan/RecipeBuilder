package org.cs564.recipeapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeSceneController {

    // SCENE BUILDER CODE /////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton beefRadioButton;

    @FXML
    private Pane browsePane;

    @FXML
    private Button browseRecipeButton;

    @FXML
    private CheckBox carrotCheckBox;

    @FXML
    private RadioButton chickenRadioButton;

    @FXML
    private CheckBox cornCheckBox;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private RadioButton eggsRadioButton;

    @FXML
    private RadioButton fishRadioButton;

    @FXML
    private CheckBox garlicCheckBox;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<?, ?> ingredCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<?, ?> minutesCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private Button nextPageButton;

    @FXML
    private CheckBox onionCheckBox;

    @FXML
    private TextField pageNumber;

    @FXML
    private CheckBox pastaCheckBox;

    @FXML
    private CheckBox pepperCheckBox;

    @FXML
    private CheckBox potatoCheckBox;

    @FXML
    private Button prevPageButton;

    @FXML
    private Pane profilePane;

    @FXML
    private ToggleGroup proteinGroup;

    @FXML
    private Button quitButton;

    @FXML
    private CheckBox riceCheckBox;

    @FXML
    private AnchorPane searchPane1;

    @FXML
    private Button searchRecipeButton;

    @FXML
    private Button searchSubmitButton1;

    @FXML
    private Button searchSubmitButton2;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane settingsPane;

    @FXML
    private CheckBox spinachCheckBox;

    @FXML
    private TableColumn<?, ?> stepsCol;

    @FXML
    private TableView<TableModel> table;

    @FXML
    private CheckBox tomatoCheckBox;

    @FXML
    void initialize() {
        assert beefRadioButton != null : "fx:id=\"beefRadioButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browsePane != null : "fx:id=\"browsePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browseRecipeButton != null : "fx:id=\"browseRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert carrotCheckBox != null : "fx:id=\"carrotCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert chickenRadioButton != null : "fx:id=\"chickenRadioButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert cornCheckBox != null : "fx:id=\"cornCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert eggsRadioButton != null : "fx:id=\"eggsRadioButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert fishRadioButton != null : "fx:id=\"fishRadioButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert garlicCheckBox != null : "fx:id=\"garlicCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert ingredCol != null : "fx:id=\"ingredCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert minutesCol != null : "fx:id=\"minutesCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert nextPageButton != null : "fx:id=\"nextPageButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert onionCheckBox != null : "fx:id=\"onionCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert pageNumber != null : "fx:id=\"pageNumber\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert pastaCheckBox != null : "fx:id=\"pastaCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert pepperCheckBox != null : "fx:id=\"pepperCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert potatoCheckBox != null : "fx:id=\"potatoCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert prevPageButton != null : "fx:id=\"prevPageButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert proteinGroup != null : "fx:id=\"proteinGroup\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert riceCheckBox != null : "fx:id=\"riceCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchPane1 != null : "fx:id=\"searchPane1\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchRecipeButton != null : "fx:id=\"searchRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchSubmitButton1 != null : "fx:id=\"searchSubmitButton1\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert spinachCheckBox != null : "fx:id=\"spinachCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert stepsCol != null : "fx:id=\"stepsCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert tomatoCheckBox != null : "fx:id=\"tomatoCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";

    }

    // MY CODE ////////////////////////////////////////////////////////////////////////////////////////////////////////

    ObservableList<TableModel> oblist = FXCollections.observableArrayList();
    ObservableList<TableModel> curPage = FXCollections.observableArrayList();
    private final int rowsPerPage = 25;
    private int pageIndex;
    private int maxPages;
    Connection connection;
    ResultSet rs;

    public void handleClicks(ActionEvent event) throws Exception {
        if (event.getSource() == homeButton) {
            profilePane.toFront();
        }
        if (event.getSource() == settingsButton) {
            settingsPane.toFront();
        }
        if (event.getSource() == browseRecipeButton) {
            browsePane.toFront();
            browseInitialize();
        }
        if (event.getSource() == searchRecipeButton) {
            searchPane1.toFront();
        }
        if (event.getSource() == logoutButton) {
            Parent scene = FXMLLoader.load(getClass().getResource("loginSceneController.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(scene));
        }
    }

    public void browseInitialize() throws Exception {

        try {
            int count = 0;
            String sqlStatement = "SELECT recipe_name, minutes, n_steps, n_ingredients, submitted FROM recipe";
            connection = DatabaseConnector.getConnection();
            rs = connection.createStatement().executeQuery(sqlStatement);

            // Add all contents to oblist for storage
            while (rs.next()) {
                oblist.add(new TableModel(rs.getString("recipe_name"), rs.getInt("minutes"), rs.getInt("n_steps"), rs.getInt("n_ingredients"), rs.getString("submitted")));
            }

            // Set pages and update table
            pageIndex = 0;
            maxPages = (oblist.size() / rowsPerPage);
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            minutesCol.setCellValueFactory(new PropertyValueFactory<>("Minutes"));
            stepsCol.setCellValueFactory(new PropertyValueFactory<>("Steps"));
            ingredCol.setCellValueFactory(new PropertyValueFactory<>("numIngredients"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
            updatePage(pageIndex);

        } catch (SQLException e) {
            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void handlePageEvent(ActionEvent event) throws SQLException {
        if (event.getSource() == nextPageButton && pageIndex != maxPages - 1) {
            updatePage(pageIndex + 1);
            pageIndex++;
        }
        if (event.getSource() == prevPageButton && pageIndex != 0) {
            updatePage(pageIndex - 1);
            pageIndex--;
        }
        if (event.getSource() == pageNumber) {
            int input = Integer.parseInt(pageNumber.getCharacters().toString());
            //System.out.println("HERE -----------");

            if (input > 0 && input < maxPages) {
                updatePage(input - 1);
                pageNumber.setPromptText(String.valueOf(pageIndex));
            }
        }
    }

    /*
     * Search will work as follows (Still working this out...)
     * Step 1: Select a basic set of options to narrow down the immense amount of recipes
     * Step 2: From the sub-table created in step 1 create a list of check boxes for ingredients
     * Step 3: Show results?
     */
    @FXML
    public void handleSearchSubmit(ActionEvent event) {
        // Step 1
        if (event.getSource() == searchSubmitButton1) {
            // Get selections
            // Get subtable of ingredients from recipes containing selections
            // Bring searchPane2 to front
            // Display other ingredients as group of check boxes
            return;
        }
        // Step 2 + 3 TODO
        if (event.getSource() == searchSubmitButton2) {
            // Get subtable of recipes containing all selections
            // Bring searchPane3 to front
            // Display subtable of recipes containing ingredients ordered ascending by the num of ingredients you have
            return;
        }
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
    }

    private void updatePage(int pageIndex) throws SQLException {
        if (oblist.size() == 0) {
            System.out.println("oblist empty");
            return;
        }

        // get start index
        int i1 = pageIndex * rowsPerPage;
        int count = 0;
        if (curPage.size() != 0) {
            while (oblist.get(i1) != null && count < rowsPerPage) {
                curPage.set(count, oblist.get(i1));
                count++;
                i1++;
            }
        } else {
            while (oblist.get(i1) != null && count < rowsPerPage) {
                curPage.add(count, oblist.get(i1));
                count++;
                i1++;
            }
        }

        table.setItems(curPage);
        pageNumber.setPromptText(String.valueOf(pageIndex));
    }

}
