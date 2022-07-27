package org.cs564.recipeapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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
    private TableColumn<Recipe, String> dateCol;

    @FXML
    private RadioButton eggsRadioButton;

    @FXML
    private RadioButton fishRadioButton;

    @FXML
    private CheckBox garlicCheckBox;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<Recipe, Integer> ingredCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<Recipe, Integer> minutesCol;

    @FXML
    private TableColumn<Recipe, String> nameCol;

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
    private Button searchSubmitButton;

    @FXML
    private Button searchSubmitButton2;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane settingsPane;

    @FXML
    private CheckBox spinachCheckBox;

    @FXML
    private TableColumn<Recipe, Integer> stepsCol;

    @FXML
    private TableView<Recipe> browseTable;

    @FXML
    private CheckBox tomatoCheckBox;

    @FXML
    private AnchorPane recipeViewPane;

    @FXML
    private Label recipeViewNameLabel;

    @FXML
    private ListView<String> ingredientListView;

    @FXML
    private TableView<Step>  stepTableView;

    @FXML
    private TableColumn<Step, Integer> stepNumColumn;

    @FXML
    private TableColumn<Step, String> stepNameColumn;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField searchTextField;

    @FXML
    private ChoiceBox<String> searchFilter;
    private String[] searchFilters = {"Name", "Tag", "Time", "Rating", "Ingredient"};

    @FXML
    private TableView<Recipe> searchTable;

    @FXML
    private TableColumn<?, ?> searchNameCol;

    @FXML
    private TableColumn<?, ?> searchMinutesCol;

    @FXML
    private TableColumn<?, ?> searchStepsCol;

    @FXML
    private TableColumn<?, ?> searchIngredientsCol;

    @FXML
    void initialize() throws Exception {
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
        assert searchSubmitButton != null : "fx:id=\"searchSubmitButton1\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert spinachCheckBox != null : "fx:id=\"spinachCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert stepsCol != null : "fx:id=\"stepsCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browseTable != null : "fx:id=\"table\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert tomatoCheckBox != null : "fx:id=\"tomatoCheckBox\" was not injected: check your FXML file 'homeSceneController.fxml'.";

        // Setup home page and connect to database
        profilePane.toFront();
        connection = DatabaseConnector.getConnection();
    }

    // MY CODE ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private double x, y; // Used for manipulating window
    ObservableList<Recipe> oblist = FXCollections.observableArrayList(); // Table list of recipes from SQL query
    ObservableList<Recipe> curPage = FXCollections.observableArrayList(); // Page of recipes from obList
    private final int rowsPerPage = 25;
    private int pageIndex;
    private int maxPages;
    Connection connection;
    ResultSet rs;

    /*
     * Handles events when user clicks menu buttons on left side of screen
     */
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
            initializeSearch();
            searchPane1.toFront();
        }
        if (event.getSource() == logoutButton) {
            Parent scene = FXMLLoader.load(getClass().getResource("loginSceneController.fxml"));
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(scene));
        }
    }

    /*
     * Initializes the browse table with all recipes from table Recipe
     */
    public void browseInitialize() throws Exception {

        try {
            int count = 0;
            String sqlStatement = "SELECT * FROM recipe";
            rs = executeQuery(sqlStatement);

            // Add all contents to oblist for storage
            while (rs.next()) {
                // If empty recipe skip
                if (rs.getInt("n_ingredients") == 0) {
                    continue;
                }
                oblist.add(new Recipe(rs.getString("recipe_name"), rs.getInt("minutes"), rs.getInt("n_steps"), rs.getInt("n_ingredients"), rs.getString("submitted"), rs.getInt("recipe_id"), rs.getString("description")));
            }

            // Set pages and update table
            pageIndex = 0;
            maxPages = Math.ceilDiv(oblist.size(), rowsPerPage);
            nameCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("name"));
            minutesCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("minutes"));
            stepsCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("n_steps"));
            ingredCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("n_ingredients"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("dateSubmitted"));
            updatePage(pageIndex);

        } catch (SQLException e) {
            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     * Initializes search pane
     */
    public void initializeSearch() {
        searchFilter.getItems().addAll(searchFilters);
    }

    /*
     * Updates current page in browse tableView when user advances page number
     */
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
     * Given a page index, this function will fill tableView with the correct recipes
     */
    private void updatePage(int pageIndex) throws SQLException {
        if (oblist.size() == 0) {
            System.out.println("oblist empty");
            return;
        }

        // Get start index
        int i = pageIndex * rowsPerPage;
        int count = 0;
        if (curPage.size() != 0) {
            while (oblist.get(i) != null && count < rowsPerPage) {
                curPage.set(count, oblist.get(i));
                count++;
                i++;
            }
        } else {
            while (oblist.get(i) != null && count < rowsPerPage) {
                curPage.add(count, oblist.get(i));
                count++;
                i++;
            }
        }

        browseTable.setItems(curPage);
        pageNumber.setPromptText(String.valueOf(pageIndex + 1));
    }

    /*
     * Handle search based on filter chosen
     */
    @FXML
    public void handleSearchSubmit(ActionEvent event) {
        // Get input
        String filter = searchFilter.getValue();
        String input = searchTextField.getText();
        String query;

        // Create query
        switch (filter) {
            case "Name":
                query = "SELECT * " +
                        "FROM Recipe r" +
                        "WHERE r.recipe_name LIKE '%" + input + "%;";
            case "Tag":
                query = "SELECT * " +
                        "FROM Recipe r INNER JOIN Tag t ON r.recipe_id = t.recipe_id" +
                        "WHERE t.tag_name LIKE '%" + input + "%;";
            case "Time":
                query = "SELECT * " +
                        "FROM Recipe r" +
                        "WHERE r.minute < " + input + ";";
            case "Rating":
                // TODO: Rating query
            case "Ingredient":
                query = "SELECT * " +
                        "FROM Recipe r INNER JOIN Ingredient i ON r.recipe_id = i.recipe_id" +
                        "WHERE i.ingredient_name LIKE '%" + input + "%;";
        }

        // Execute query and populate table

    }

    /**
     * Returns a sub-table/result set given an SQL query
     * @param query
     * @return
     */
    public ResultSet executeQuery(String query) throws SQLException {
        if (query == null || query.isEmpty()) {
            return null;
        }
        return connection.createStatement().executeQuery(query);
    }

    /**
     * Opens recipe view pane when user selects recipe from table in browse or search
     * @param event
     */
    @FXML
    public void selectRecipe(MouseEvent event) throws SQLException {
        if (event.getClickCount() > 1) {
            // TODO CLEAR RECIPE DATA FROM PREVIOUS

            // Grab recipe from row
            Recipe selectedRecipe = browseTable.getSelectionModel().getSelectedItem();

            // Execute SQL query to find ingredient and steps data from recipe in table
            String ingredientQuery = "SELECT * FROM Ingredient i WHERE i.recipe_id = " + String.valueOf(selectedRecipe.recipe_id);
            ResultSet ingredientData = executeQuery(ingredientQuery);
            String stepQuery = "SELECT * FROM Step s WHERE s.recipe_id = " + String.valueOf(selectedRecipe.recipe_id);
            ResultSet stepData = executeQuery(stepQuery);

            // Set ingredients
            while (ingredientData.next()) {
                ingredientListView.getItems().add(ingredientData.getString("ingredient_name"));
            }

            // Set steps
            stepNumColumn.setCellValueFactory(new PropertyValueFactory<Step, Integer>("step_num"));
            stepNameColumn.setCellValueFactory(new PropertyValueFactory<Step, String>("name"));
            while (stepData.next()) {
                stepTableView.getItems().add(new Step(stepData.getInt("step_num"), stepData.getString("step_name")));
            }

            // Set remaining values and descriptions
            recipeViewNameLabel.setText(selectedRecipe.name.substring(0, 1).toUpperCase() + selectedRecipe.name.substring(1));
            if (!selectedRecipe.description.isEmpty()) {
                descriptionTextArea.setText(selectedRecipe.description);
            }

            // Bring recipeViewPane to front
            recipeViewPane.toFront();
        }
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
    }
}
