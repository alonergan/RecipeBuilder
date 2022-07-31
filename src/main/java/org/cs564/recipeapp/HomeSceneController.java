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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.cs564.recipeapp.Users.deleteAccount;

public class HomeSceneController {

    // SCENE BUILDER CODE /////////////////////////////////////////////////////////////////////////////////////////////
//    @FXML
//    private ResourceBundle resources;

//    @FXML
//    private URL location;
    @FXML
    public AnchorPane pantryPane;
    @FXML
    public TableColumn step2IngredientName;
    @FXML
    public TableView step2IngredientList;
    @FXML
    public Button searchSubmitButton2;
    @FXML
    public AnchorPane appPane;
    @FXML
    public Button pantryButton;
    @FXML
    public Button confirmDeletionButton;
    @FXML
    public Button cancelDeletionButton;
    @FXML
    public TableView inventoryListView;
    @FXML
    private TableColumn<Recipe, String> dateCol;
    @FXML
    private Button homeButton;
    @FXML
    private TableColumn<Recipe, Integer> n_ingredients_Col;
    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn<Recipe, Integer> minutesCol;
    @FXML
    private TableColumn<Recipe, String> nameCol;
    @FXML
    private Button nextPageButton;
    @FXML
    private TextField pageNumber;
    @FXML
    private Button prevPageButton;
    @FXML
    private Pane profilePane;
    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private Button searchRecipeButton;
    @FXML
    private Button searchSubmitButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Pane settingsPane;
    @FXML
    private AnchorPane confirmDeletionPane;
    @FXML
    private TableColumn<Recipe, Integer> stepsCol;
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
    @FXML
    private TableView<Recipe> searchTable;

    @FXML
    private Label filterDescriptionTextBox;

    // Global variables
    private final String[] searchFilters = {"All Recipes", "Name", "Tag", "Time", "Rating", "Ingredient"};
    private double x, y; // Used for manipulating window
    public ObservableList<Recipe> obj_list = FXCollections.observableArrayList(); // Table list of recipes from SQL query
    public ObservableList<Recipe> curPage = FXCollections.observableArrayList(); // Page of recipes from obList
    public ObservableList<?> pantryList = FXCollections.observableArrayList(); // List of ingredients user has in kitchen
    private final int rowsPerPage = 25;
    private int pageIndex;
    private int maxPages;
    public Connection connection;
    public ResultSet rs;
    @FXML
    void initialize() throws Exception {
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert n_ingredients_Col != null : "fx:id=\"n_ingredients_col\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert minutesCol != null : "fx:id=\"minutesCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert nextPageButton != null : "fx:id=\"nextPageButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert pageNumber != null : "fx:id=\"pageNumber\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert prevPageButton != null : "fx:id=\"prevPageButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchPane != null : "fx:id=\"searchPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchRecipeButton != null : "fx:id=\"searchRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchSubmitButton != null : "fx:id=\"searchSubmitButton1\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert stepsCol != null : "fx:id=\"stepsCol\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert deleteUserButton != null : "fx:id=\"deleteUserButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert confirmDeletionPane != null : "fx:id=\"confirmDeletionPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert pantryPane != null : "fx:id=\"pantryPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        searchFilter.getItems().addAll(searchFilters);
        // Setup home page and connect to database
        profilePane.toFront();
        connection = DatabaseConnector.getConnection();
    }

    // END SCENE BUILDER CODE AND GLOBAL VARIABLE INITIALIZATION, ASSIGNMENT //////////////////////////////////

    ///////////////////////////////////
    /// APP MANAGEMENT FUNCTIONS ↓  ///
    /// Functions that exclusively  ///
    /// Modify and initialize views ///
    /// Independent of queries      ///
    ///////////////////////////////////

    /**
     * Handles events when user clicks menu buttons on left side of screen
     */
    @FXML
    public void handleMenuClicks(ActionEvent event) throws Exception {
        Object eventSource = event.getSource();
        if (eventSource == homeButton) {
            profilePane.toFront();
        }
        if (eventSource == settingsButton) {
            settingsPane.toFront();
        }
        if (eventSource == pantryButton) {
            pantryPane.toFront();
        }
        if (eventSource == searchRecipeButton) {
            searchSubmitButton.setDisable(true);
            searchPane.toFront();
        }
        if (eventSource == logoutButton) {
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginSceneController.fxml")));
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/style.css")).toExternalForm());
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(scene));
        }
    }

    /**
     * Main function for settings/options pane
     * Add more settings later?
     */
    public void handleSettingsClicks(ActionEvent event) {
        Object eventSource = event.getSource();
        if (eventSource == deleteUserButton) {
            confirmDeletionPane.toFront();
            return;
        }
        if (eventSource == confirmDeletionButton) {
            deleteAccount("branden");
            return;
        }
        if (eventSource == cancelDeletionButton) {
            confirmDeletionPane.toBack();
        }
    }
    /**
     * Change whether searchTextField is enabled, depending on searchFilter
     */
    @FXML
    public void handleSearchFilterEvent() {
        String filter = searchFilter.getValue();
        String description = " ";
        switch (filter) {
            case "Time":
                description = "Search for recipes under _ minutes:";
                break;
            case "Rating":
                description = "Search for recipes at least rating:";
                break;
            case "Ingredient":
                description = "Search for recipes containing:";
                break;
            default:
                description = " ";
        }
        filterDescriptionTextBox.setText(description);
    }
    /**
     * Updates current page in browse tableView when user advances page number
     */
    @FXML
    public void handlePageEvent(ActionEvent event) {
        Object eventSource = event.getSource();
        if (eventSource == nextPageButton && pageIndex != maxPages - 1) {
            updatePage(pageIndex + 1);
            pageIndex++;
            return;
        }
        if (eventSource == prevPageButton && pageIndex != 0) {
            updatePage(pageIndex - 1);
            pageIndex--;
            return;
        }
        if (eventSource == pageNumber) {
            assert pageNumber != null;
            int input = Integer.parseInt(pageNumber.getCharacters().toString());
            //System.out.println("HERE -----------");

            if (input > 0 && input < maxPages) {
                updatePage(input - 1);
                pageNumber.setPromptText(String.valueOf(pageIndex));
            }
        }
    }

    /**
     * Given a page index, this function will fill tableView with the correct recipes or reviews
     */
    private void updatePage(int pageIndex) {
        if (obj_list.size() == 0) {
            System.out.println("oblist empty");
            return;
        }

        // Get start index
        int i = pageIndex * rowsPerPage;
        int count = 0;
        if (curPage.size() != 0) {
            while (obj_list.get(i) != null && count < rowsPerPage) {
                curPage.set(count, obj_list.get(i));
                count++;
                i++;
            }
        } else {
            while (obj_list.get(i) != null && count < rowsPerPage) {
                curPage.add(count, obj_list.get(i));
                count++;
                i++;
            }
        }

        searchTable.setItems(curPage);
        pageNumber.setPromptText(String.valueOf(pageIndex + 1));
    }

    /**
     * Set up tableview based on ResultSet rs
     * @throws SQLException on error retrieving data from populated resultSet
     */
    private void constructRecipeTable(TableView<Recipe> recipeTableView) throws SQLException {
        // Clear obj_list, and add contents
        obj_list.clear();
        while (rs.next()) {
            // If an empty recipe, skip
            if (rs.getInt("n_ingredients") == 0) {
                continue;
            }
            obj_list.add(new Recipe(rs.getString("recipe_name"),
                    rs.getInt("minutes"), rs.getInt("n_steps"),
                    rs.getInt("n_ingredients"), rs.getString("submitted"),
                    rs.getInt("recipe_id"), rs.getString("description")));
        }

        // Set pages and update table
        pageIndex = 0;
        maxPages = Math.ceilDiv(obj_list.size(), rowsPerPage);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<>("minutes"));
        stepsCol.setCellValueFactory(new PropertyValueFactory<>("n_steps"));
        n_ingredients_Col.setCellValueFactory(new PropertyValueFactory<>("n_ingredients"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateSubmitted"));
        updatePage(pageIndex);
    }
    /**
     * Close application
     */
    @FXML
    public void quitApplication() {
        Platform.exit();
    }

    ////////////////////////////////
    /// BROWSE/QUERY FUNCTIONS ↓ ///
    /// Execute queries, call UI ///
    /// Functions to update scene///
    ////////////////////////////////

    /**
     * Returns a sub-table/result set given an SQL query
     * @param query The SQL query
     * @return the result set, if successful
     */
    public ResultSet executeQuery(String query) throws SQLException {
        if (query == null || query.isEmpty()) {
            return null;
        }
        return connection.createStatement().executeQuery(query);
    }

    /**
     * Handle search based on filter chosen
     */
    @FXML
    public void handleSearchSubmit() {
        // Get input
        String filter = searchFilter.getValue();
        String input = searchTextField.getText();
        String query = null;

        // Create query
        switch (filter) {
            case "All Recipes":
                query = "SELECT * FROM recipe;";
                break;
            case "Name":
                query = "SELECT * " +
                        "FROM Recipe r " +
                        "WHERE r.recipe_name LIKE '%" + input + "%';";
                break;
            case "Tag":
                query = "SELECT r.* " +
                        "FROM Recipe r, Tag t " +
                        "WHERE r.recipe_id = t.recipe_id AND t.tag_name LIKE '%" + input + "%';";
                break;
            case "Time":
                query = "SELECT * " +
                        "FROM Recipe r " +
                        "WHERE r.minutes < " + input + ";";
                break;
            case "Rating":
                double rating = Double.parseDouble(input);
                if (rating < 0 || rating > 5)
                    return;
                // Maybe add some more UI effects to remind the user to enter a valid number.
                query = "SELECT r.* " +
                        "FROM Recipe r " +
                        "WHERE r.recipe_id IN ( " +
                                "SELECT recipe_id " +
                                "FROM ( " +
                                    "SELECT recipe_id, AVG(rating) AS average FROM Review " +
                                    "GROUP BY recipe_id " +
                                    ") reviewAverages " +
                                "WHERE average > "+ rating +
                                ");";
                break;
            case "Ingredient":
                query = "SELECT r.* " +
                        "FROM Recipe r INNER JOIN Ingredient i ON r.recipe_id = i.recipe_id" +
                        "WHERE i.ingredient_name LIKE '%" + input + "%';";
                break;
        }

        // Execute query and populate table
        try {
            if (query == null) return;
            rs = executeQuery(query);
            if (!filter.equals("Rating"))
                constructRecipeTable(searchTable);
            else ;  //TODO: construct review table?
            System.out.println("DONE??\n");
        } catch (SQLException e) {
            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Opens recipe view pane when user selects recipe from table in browse or search
     * @param event The event triggered by being clicked
     */
    @FXML
    public void selectRecipe(MouseEvent event) throws SQLException {
        if (event.getClickCount() > 1) {
            // TODO CLEAR RECIPE DATA FROM PREVIOUS

            // Grab recipe from row
            Recipe selectedRecipe = searchTable.getSelectionModel().getSelectedItem();

            // Execute SQL query to find ingredient and steps data from recipe in table
            String ingredientQuery = "SELECT * FROM Ingredient i WHERE i.recipe_id = " + selectedRecipe.recipe_id;
            ResultSet ingredientData = executeQuery(ingredientQuery);
            String stepQuery = "SELECT * FROM Step s WHERE s.recipe_id = " + selectedRecipe.recipe_id;
            ResultSet stepData = executeQuery(stepQuery);

            // Set ingredients
            while (ingredientData.next()) {
                ingredientListView.getItems().add(ingredientData.getString("ingredient_name"));
            }

            // Set steps
            stepNumColumn.setCellValueFactory(new PropertyValueFactory<>("step_num"));
            stepNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
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
}
