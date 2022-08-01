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

public class HomeSceneController {

    // SCENE BUILDER CODE /////////////////////////////////////////////////////////////////////////////////////////////
//    @FXML
//    private ResourceBundle resources;

//    @FXML
//    private URL location;
    @FXML
    public AnchorPane pantryPane;
//    @FXML
//    public TableColumn step2IngredientName;
//    @FXML
//    public TableView step2IngredientList;
//    @FXML
//    public Button searchSubmitButton2;
    @FXML
    public AnchorPane appPane;
    @FXML
    public Button pantryButton;
    @FXML
    public Button confirmDeletionButton;
    @FXML
    public Button cancelDeletionButton;
//    @FXML
//    public TableView inventoryListView;
    @FXML
    public Spinner<Integer> pantrySearchSpinner;
    @FXML
    public Button pantrySearchRecipesBtn;
    @FXML
    public Button pantrySearchIngredientBtn;
    @FXML
    public TextField pantryAddField;
    @FXML
    public Button pantryAddBtn;
    @FXML
    public ListView<String> pantryList;
    @FXML
    public Button pantryDeleteBtn;
    @FXML
    public Label searchTableSizeLabel;
    @FXML
    public ListView<String> pantrySearchList;
    @FXML
    public Button pantryCancelButton;
    @FXML
    public AnchorPane pantrySearchListAnchor;
    @FXML
    public AnchorPane pantryListAnchor;
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
    private TableColumn<Recipe, Double> ratingCol;
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
    public ObservableList<Recipe> recipeObvList = FXCollections.observableArrayList(); // Table list of recipes from SQL query
    public ObservableList<Recipe> recipeCurPage = FXCollections.observableArrayList(); // Page of recipes from obList
//    public ObservableList<String> pantryObvList = FXCollections.observableArrayList(); // List of ingredients user has in kitchen
    private SpinnerValueFactory.IntegerSpinnerValueFactory pantrySpinnerValues; // corresponds to minimum ingredients required in search
    private final int rowsPerPage = 27;
    private int pageIndex;
    private int maxPages;
    public Connection connection;
    public ResultSet rs;
    public String username = "";

    /**
     * TODO: add remaining assertions
     * @throws Exception for connecting to MySQL database
     */
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

        // Setup home page and connect to database
        profilePane.toFront();
        searchFilter.getItems().addAll(searchFilters);
        searchFilter.setValue(searchFilter.getItems().get(0));
        // connection = DatabaseConnector.getConnection();
    }

    // END SCENE BUILDER CODE AND GLOBAL VARIABLE INITIALIZATION, ASSIGNMENT //////////////////////////////////

    /**
     * Assign username, set up pantry/inventory list
     * TODO: Favorites?
     * @param usr the username passed from LoginSceneController. Had issues loading a controller with a
     *            parameterized constructor
     */
    protected void setupUserComponents(String usr, Connection connection) {
        this.connection = connection;
        username = usr;
        // Construct user's inventoryList
        try {
            rs = executeQuery("SELECT ingredient_name FROM User WHERE username = '" + username + "';");
            while (rs.next()) {
                pantryList.getItems().add(rs.getString(1));
            }
            int size = pantryList.getItems().size();
            pantrySpinnerValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Math.max(size, 1), 1);
            pantrySearchSpinner.setValueFactory(pantrySpinnerValues);
//            if (size == 0)
//                pantrySearchSpinner.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            return;
        }
        if (eventSource == settingsButton) {
            settingsPane.toFront();
            return;
        }
        if (eventSource == pantryButton) {
            pantryPane.toFront();
            return;
        }
        if (eventSource == searchRecipeButton) {
            searchPane.toFront();
            return;
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
            try {
                // delete username from User
                String query = "DELETE FROM User WHERE username='" + username + "';";
                connection.createStatement().executeQuery(query);
                // Switch to LoginScene
                Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginSceneController.fxml")));
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/style.css")).toExternalForm());
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(new Scene(scene));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (eventSource == cancelDeletionButton) {
            confirmDeletionPane.toBack();
        }
    }

    /**
     * handle clicks that occur in pantryPane
     * TODO: make checking pantryList better;
     * Make a custom ListView to go with the custom ListCell
     * @param event the events that correspond to the buttons' clicks
     */
    public void handlePantryClicks(ActionEvent event) {
        String input = pantryAddField.getText();
        Object eventSource = event.getSource();
        String query = "";
        try {
            if (eventSource == pantryAddBtn) {  //TODO: any error cases?
                if (!input.equals("")) {
                    pantryList.getItems().add(input);
                    pantryList.refresh();
                    // handle error handling for duplicate value
                    query = "INSERT INTO User VALUES('" + username + "', '" + input + "');";
                    executeUpdate(query);
                    // Adjust the maximum number of ingredients to search by
                    int max = pantryList.getItems().size();
                    pantrySpinnerValues.setMax(max);
                }
                return;
            }
            if (eventSource == pantrySearchRecipesBtn && pantryList.getItems().size() > 0) {
                searchPane.toFront();
                query = "SELECT r.* " +
                        "FROM recipe r, ingredient i " +
                        "WHERE r.recipe_id = i.recipe_id and i.ingredient_name IN( " +
                            "SELECT ingredient_name FROM user WHERE username = '" + username + "') " +
                        "GROUP BY i.recipe_id HAVING COUNT(*) >= " + pantrySearchSpinner.getValue() + ";";
                rs = executeQuery(query);
                constructRecipeTable();
                return;
            }
            if (eventSource == pantrySearchIngredientBtn) {
//                pantrySearchListAnchor.toFront();
//                pantrySearchListAnchor.setDisable(false);
//                pantrySearchListAnchor.setVisible(true);
//                pantryListAnchor.setDisable(true);
//                pantrySearchIngredientBtn.setDisable(false);
//                pantryListAnchor.setVisible(false);
//                pantryListAnchor.setVisible();
//                query = "SELECT ingredient_name " +
//                        "FROM ingredient WHERE ingredient_name LIKE '%" + input + "%';";
//                pantrySearchList.getItems().clear();
//                while (rs.next()) {
//                    pantrySearchList.getItems().add(rs.getString(1));
//                }
//                pantrySearchList.refresh();
//                rs = executeQuery(query);
                return;
            }
            if (eventSource == pantryDeleteBtn) {
                int index = pantryList.getSelectionModel().getSelectedIndex();
                String indexStr = pantryList.getItems().get(index);
                query = "SELECT * FROM User WHERE " +
                        "username = '" + username + "' AND ingredient_name= '" + indexStr + "';";
                rs = executeQuery(query);
                if (rs.next()) {
                    query = "DELETE FROM User WHERE " +
                            "username = '" + username + "' AND ingredient_name= '" + indexStr + "';";
                    executeUpdate(query);
                    pantryList.getItems().remove(index);
                    int max = pantrySpinnerValues.getMax();
                    pantrySpinnerValues.setMax(max <= 2 ? max = 1 : max - 1);
                    pantryList.refresh();
                    return;
                }
            }
//            if (eventSource == pantryCancelButton) {
//                pantrySearchListAnchor.setDisable(true);
//                pantrySearchListAnchor.setVisible(false);
//                pantryListAnchor.setDisable(false);
//                pantryListAnchor.setVisible(true);
//                pantryListAnchor.toFront();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        if (recipeObvList.size() == 0) {
            System.out.println("oblist empty");
            return;
        }

        // Get start index
        int i = pageIndex * rowsPerPage;
        int count = 0;
        if (recipeCurPage.size() != 0) {
            while (recipeObvList.get(i) != null && count < rowsPerPage) {
                recipeCurPage.set(count, recipeObvList.get(i));
                count++;
                i++;
            }
        } else {
            while (recipeObvList.get(i) != null && count < rowsPerPage) {
                recipeCurPage.add(count, recipeObvList.get(i));
                count++;
                i++;
            }
        }

        searchTable.setItems(recipeCurPage);
        pageNumber.setPromptText(String.valueOf(pageIndex + 1));
    }

    /**
     * Set up tableview based on ResultSet rs
     * @throws SQLException on error retrieving data from populated resultSet
     */
    private void constructRecipeTable() throws SQLException {
        // Clear recipeObvList, and add contents
        recipeObvList.clear();
        while (rs.next()) {
            // If an empty recipe, skip
            if (rs.getInt("n_ingredients") == 0) {
                continue;
            }

            // Get rating for each recipe TODO: Get average rating for each recipe_id
            //System.out.println("Getting rating");
            //String getRecipeRating = "SELECT AVG(rating) rating FROM Review r WHERE r.recipe_id = " + rs.getInt("recipe_id") + ";";
            //ResultSet rating = executeQuery(getRecipeRating);
            double rating = 0.0;

            // Add new recipe
            recipeObvList.add(new Recipe(rs.getString("recipe_name"),
                    rs.getInt("minutes"), rs.getInt("n_steps"),
                    rs.getInt("n_ingredients"), rs.getString("submitted"),
                    rs.getInt("recipe_id"), rs.getString("description"), rating));
        }

        // Set pages and update table
        pageIndex = 0;
        maxPages = Math.ceilDiv(recipeObvList.size(), rowsPerPage);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<>("minutes"));
        stepsCol.setCellValueFactory(new PropertyValueFactory<>("n_steps"));
        n_ingredients_Col.setCellValueFactory(new PropertyValueFactory<>("n_ingredients"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateSubmitted"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        updatePage(pageIndex);
        String resultSize = "Result Table: " + recipeObvList.size() + " entries";
        searchTableSizeLabel.setText(resultSize);   //TODO: test
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
    /**
     * Returns a sub-table/result set given an SQL query
     * @param query The SQL query
     * @return the result set, if successful
     */
    public ResultSet executeQuery(String query) throws SQLException{
        if (query == null || query.isEmpty()) {
            return null;
        }
        return connection.createStatement().executeQuery(query);
    }

    /**
     * Updates a table
     */
    public void executeUpdate(String cmd) throws SQLException {
        if (cmd == null || cmd.isEmpty()) {
            return;
        }
        connection.createStatement().executeUpdate(cmd);
    }

    /**
     * Handle search based on filter chosen in searchPane
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
            constructRecipeTable();
        } catch (SQLException e) {
            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
