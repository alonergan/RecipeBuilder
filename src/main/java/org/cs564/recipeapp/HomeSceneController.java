package org.cs564.recipeapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    @FXML
    public AnchorPane appPane;
    @FXML
    public Button pantryButton;
    @FXML
    public Button confirmDeletionButton;
    @FXML
    public Button cancelDeletionButton;
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
    public Label pantryMessageLabel;
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
    @FXML
    private ProgressBar ratingBar;
    @FXML
    private Label ratingBarLabel;
    @FXML
    private Button setUpSQLButton;
    @FXML
    private Button deleteRecipeButton;
    @FXML
    private TextField confirmUsernameTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private AnchorPane sqlInitAnchorPane;
    @FXML
    private Button browseFilesButton;
    @FXML
    private Button submitPathButton;
    @FXML
    private TextField pathToCSV;
    @FXML
    private Button favoriteButton;
    @FXML
    private Button unfavoriteButton;
    @FXML
    private Label searchTimeLabel;
    @FXML
    private Label constructTableTimeLabel;
    @FXML
    private TextField sqlUsernameTextField;
    @FXML
    private TextField sqlPasswordTextField;
    @FXML
    private Label favoriteSuccessLabel;

    // Global variables
    public Stage stage;
    private final String[] searchFilters = {"All Recipes", "Name", "Tag", "Time", "Rating", "Ingredient"};
    private double x, y; // Used for manipulating window
    public ObservableList<Recipe> recipeObvList = FXCollections.observableArrayList(); // Table list of recipes from SQL query
    public ObservableList<Recipe> recipeCurPage = FXCollections.observableArrayList(); // Page of recipes from obList
    private SpinnerValueFactory.IntegerSpinnerValueFactory pantrySpinnerValues; // corresponds to minimum ingredients required in search
    private final int rowsPerPage = 27;
    private int pageIndex;
    private int maxPages;
    public Connection connection;
    public ResultSet rs;
    public ResultSet averageRatings;
    private User currentUser;
    public int selectedRecipeID;
    private boolean isPantryListFront = true;  // if pantryListAnchor (inventory) is in front of pantryListSearchAnchor (search by ingredient)
    public File csvPath; // paths to csv files for data
    /**
     * TODO: add remaining assertions
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
    }

    // END SCENE BUILDER CODE AND GLOBAL VARIABLE INITIALIZATION, ASSIGNMENT //////////////////////////////////

    /**
     * Assign username, set up pantry/inventory list
     * TODO: Favorites?
     */
    protected void setupUserComponents(String username, String password, Connection connection) {
        this.connection = connection;
        this.currentUser = new User(username, password);
        // Construct user's inventoryList
        try {
            rs = executeQuery("SELECT ingredient_name FROM inPantry WHERE username = '" + currentUser.username + "';");
            while (rs.next()) {
                pantryList.getItems().add(rs.getString(1));
            }
            int size = pantryList.getItems().size();
            pantrySpinnerValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Math.max(size, 1), 1);
            pantrySearchSpinner.setValueFactory(pantrySpinnerValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////
    /// APP MANAGEMENT FUNCTIONS ↓  ///
    /// Functions that exclusively  ///
    /// Modify and initialize views ///
    /// or handle events            ///
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
            changePantryView(true);
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
                String query = "DELETE FROM User WHERE username='" + currentUser.username + "';";
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
     * Instead of using an extra pane/"page" for searching by ingredients in the pantryPane,
     * enable/disable, set visibility, push to front
     * @param pantryInFront = false if searching by ingredient, true if canceling search
     */
    private void changePantryView(boolean pantryInFront) {
        isPantryListFront = pantryInFront;
        pantryMessageLabel.setText("");
        // if searching by ingredient
        if (!isPantryListFront) {
            pantrySearchListAnchor.toFront();
            pantryDeleteBtn.setDisable(true);
        }
        else {
            pantryListAnchor.toFront();
            pantryDeleteBtn.setDisable(false);
        }
    }

    /**
     * handle clicks that occur in pantryPane
     * TODO: test, consider potentially failing cases
     * Make a custom ListView to go with the custom ListCell
     * @param event the events that correspond to the buttons' clicks
     */
    public void handlePantryClicks(ActionEvent event) throws SQLException {
        String input = pantryAddField.getText();
        Object eventSource = event.getSource();
        String message;
        Statement statement = connection.createStatement();
        try {
            // Add an ingredient to the pantry
            String query;
            if (eventSource == pantryAddBtn) {
                // if we're adding ingredients from resulting list; re-assign input
                if (!isPantryListFront) {
                    int index = pantrySearchList.getSelectionModel().getSelectedIndex();
                    input = pantrySearchList.getItems().get(index);
                }
                // otherwise we're adding ingredients directly (without search)
                else if (input.equals(""))
                    return;
                // check if we already have the ingredient; resolves duplicate insert SQLException
                if (pantryList.getItems().contains(input))
                    return;
                pantryList.getItems().add(input);
                pantryList.refresh();
                // handle error handling for duplicate value
                query = "INSERT INTO inPantry VALUES('" + currentUser.username + "', '" + input + "');";
                statement.executeUpdate(query);
                // adjust the maximum number of minimum ingredients in inventory for searching recipes
                int max = pantryList.getItems().size();
                pantrySpinnerValues.setMax(max);

                message = "Added " + input + " to inventory";
                pantrySpinnerValues.setValue(pantrySpinnerValues.getValue() + 1);
                pantryMessageLabel.setText(message);
                return;
            }
            // Switch views; go to recipe searchPane
            if (eventSource == pantrySearchRecipesBtn) {
                if (pantryList.getItems().size() > 0) {
                    pantryMessageLabel.setText("Error: inventory is empty");
                    return;
                }
                searchPane.toFront();
                query = "SELECT r.* " +
                        "FROM recipe r, ingredient i " +
                        "WHERE r.recipe_id = i.recipe_id and i.ingredient_name IN( " +
                            "SELECT ingredient_name FROM inPantry WHERE username = '" + currentUser.username + "') " +
                        "GROUP BY i.recipe_id HAVING COUNT(*) >= " + pantrySearchSpinner.getValue() + ";";
                rs = executeQuery(query);
                constructRecipeTable();
                return;
            }

            if (eventSource == pantrySearchIngredientBtn && !input.equals("")) {
                message = "Searching for ingredients similar to " + input;
                pantryMessageLabel.setText(message);
                changePantryView(false);
                query = "SELECT DISTINCT ingredient_name " +
                        "FROM ingredient WHERE ingredient_name LIKE '%" + input + "%';";
                rs = executeQuery(query);
                pantrySearchList.getItems().clear();
                while (rs.next()) {
                    pantrySearchList.getItems().add(rs.getString(1));
                }
                pantrySearchList.refresh();
                return;
            }
            if (eventSource == pantryDeleteBtn && isPantryListFront) {
                int index = pantryList.getSelectionModel().getSelectedIndex();
                if (index < 0) {return;}
                input = pantryList.getItems().get(index);
                query = "SELECT * FROM inPantry WHERE " +
                        "username = '" + currentUser.username + "' AND ingredient_name= '" + input + "';";
                rs = executeQuery(query);
                if (rs.next()) {
                    query = "DELETE FROM inPantry WHERE " +
                            "username = '" + currentUser.username + "' AND ingredient_name= '" + input + "';";
                    statement.executeUpdate(query);
                    pantryList.getItems().remove(index);
                    int max = pantrySpinnerValues.getMax();
                    pantrySpinnerValues.setMax(max <= 2 ? 1 : max - 1);
                    pantryList.refresh();
                    message = "Remove " + input + "was successful";
                    pantryMessageLabel.setText(message);
                }
                else {
                    message = "Failed to remove " + input;
                    pantryMessageLabel.setText(message);
                }
                return;
            }
            if (eventSource == pantryCancelButton) {
                changePantryView(true);
            }
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
        String description = switch (filter) {
            case "Time" -> "Search for recipes under _ minutes:";
            case "Rating" -> "Search for recipes at least rating:";
            case "Ingredient" -> "Search for recipes containing:";
            default -> " ";
        };
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
            System.out.println("observableList empty");
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

        // Get average ratings table
        String getRecipeRating = "SELECT recipe_id, AVG(rating) rating FROM Review r GROUP BY recipe_id;";
        averageRatings = executeQuery(getRecipeRating);

        while (rs.next()) {
            // If an empty recipe, skip
            if (rs.getInt("n_ingredients") == 0) {
                continue;
            }

            // Get rating for each recipe
            double rating = 0.0;
            if (averageRatings.next()) {
                rating = averageRatings.getDouble("rating");
            }

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
        String resultSize = "Results: " + recipeObvList.size();
        searchTableSizeLabel.setText(resultSize);   //TODO: test
    }

    /**
     * TODO: Get root username and password and pass them to DatabaseConnector
     * Handles clicks for buttons on user menu when setting up server
     * @param event
     */
    @FXML
    void handleSetupClicks(Event event) throws Exception {
        if (event.getSource() == setUpSQLButton) {
            sqlInitAnchorPane.toFront();
        }
        if (event.getSource() == browseFilesButton) {
            DirectoryChooser chooser = new DirectoryChooser();
            csvPath = chooser.showDialog(sqlInitAnchorPane.getScene().getWindow());
            if (csvPath != null) {
                pathToCSV.setText(csvPath.getAbsolutePath());
            }
        }
        if (event.getSource() == submitPathButton) {
            String username = sqlUsernameTextField.getText();
            String password = sqlPasswordTextField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                Alert loginError = new Alert(Alert.AlertType.ERROR);
                loginError.setContentText("You must enter a valid username and password");
                loginError.showAndWait();
                return;
            }

            if (DatabaseConnector.initializeDatabase(csvPath, username, password)) {
                System.out.println("Success initializing database");
                profilePane.toFront();
            }
        }
    }

    @FXML
    public void handleFavoriteButton(Event event) throws SQLException {
        if (event.getSource() == favoriteButton) {
            String query = "SELECT recipe_id FROM Favorites WHERE username = '" + currentUser.username + "' AND recipe_id = " + selectedRecipeID + ";";
            rs = executeQuery(query);
            // If found
            if (rs.next()) {
                favoriteSuccessLabel.setText("You have already favorited this recipe");
                return;
            } else {
                // Add recipe
                query = "INSERT INTO Favorites VALUES('" + currentUser.username + "', " + selectedRecipeID + ");";
                connection.createStatement().executeUpdate(query);
                System.out.println("Added to favorites");
                favoriteSuccessLabel.setText("Successfully added to favorites");
                return;
            }
        }
        if (event.getSource() == unfavoriteButton) {
            String query = "SELECT * FROM Favorites WHERE username = '" + currentUser.username + "' AND recipe_id = " + selectedRecipeID + ";";
            rs = executeQuery(query);
            if (rs.next()) {
                query = "DELETE FROM Favorites WHERE username = '" + currentUser.username + "' AND recipe_id = " + selectedRecipeID + ";";
                connection.createStatement().executeUpdate(query);
                favoriteSuccessLabel.setText("Recipe successfully unfavorited");
            } else {
                favoriteSuccessLabel.setText("You have not favorited this recipe");
            }
        }
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
    public void selectRecipe(MouseEvent event) {
        if (event.getClickCount() > 1) {
            // TODO CLEAR RECIPE DATA FROM PREVIOUS
            try {
                // Grab recipe from row
                Recipe selectedRecipe = searchTable.getSelectionModel().getSelectedItem();
                selectedRecipeID = selectedRecipe.recipe_id;

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
                double rating = selectedRecipe.rating / 5.0;
                ratingBar.setProgress(rating);
                ratingBarLabel.setText("Rating " + selectedRecipe.rating + " / 5.0");
                favoriteSuccessLabel.setText(" ");

                // Bring recipeViewPane to front
                recipeViewPane.toFront();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            case "All Recipes" -> query = "SELECT * FROM recipe;";
            case "Name" -> query = "SELECT * " +
                    "FROM Recipe r " +
                    "WHERE r.recipe_name LIKE '%" + input + "%';";
            case "Tag" -> query = "SELECT r.* " +
                    "FROM Recipe r, Tag t " +
                    "WHERE r.recipe_id = t.recipe_id AND t.tag_name LIKE '%" + input + "%';";
            case "Time" -> query = "SELECT * " +
                    "FROM Recipe r " +
                    "WHERE r.minutes < " + input + ";";
            case "Rating" -> {
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
                        "WHERE average > " + rating +
                        ");";
            }
            case "Ingredient" -> query = "SELECT r.* " +
                    "FROM Recipe r INNER JOIN Ingredient i ON r.recipe_id = i.recipe_id" +
                    "WHERE i.ingredient_name LIKE '%" + input + "%';";
        }

        // Execute query and populate table
        try {
            if (query == null) return;
            long startTime = System.currentTimeMillis();
            rs = executeQuery(query);
            long endTime = System.currentTimeMillis();
            searchTimeLabel.setText("Query Time: " + String.valueOf((endTime - startTime) / 1000.0) + "s");

            startTime = System.currentTimeMillis();
            constructRecipeTable();
            endTime = System.currentTimeMillis();
            constructTableTimeLabel.setText("Construct Table Time: " + String.valueOf((endTime - startTime) / 1000.0) + "s");
        } catch (SQLException e) {
            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
