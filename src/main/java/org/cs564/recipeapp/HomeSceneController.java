package org.cs564.recipeapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeSceneController {

    @FXML
    private Pane browsePane;

    @FXML
    private Button browseRecipeButton;

    @FXML
    private TableColumn<TableModel, String> dateCol;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<TableModel, Integer> ingredCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<TableModel, Integer> minutesCol;

    @FXML
    private TableColumn<TableModel, String> nameCol;

    @FXML
    private Pane profilePane;

    @FXML
    private Button searchRecipeButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane settingsPane;

    @FXML
    private TableColumn<TableModel, Integer> stepsCol;

    @FXML
    private TableView<TableModel> table;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button prevPageButton;

    @FXML
    private TextField pageNumber;

    ObservableList<TableModel> oblist = FXCollections.observableArrayList();
    ObservableList<TableModel> curPage = FXCollections.observableArrayList();
    private final int rowsPerPage = 25;
    private int pageIndex;
    private int maxPages;
    Connection connection;
    ResultSet rs;


    @FXML
    void initialize() {
        assert browsePane != null : "fx:id=\"browsePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert browseRecipeButton != null : "fx:id=\"browseRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert searchRecipeButton != null : "fx:id=\"searchRecipeButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'homeSceneController.fxml'.";
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'homeSceneController.fxml'.";
    }

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
        if (event.getSource() == logoutButton) {
            Parent scene = FXMLLoader.load(getClass().getResource("loginSceneController.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(scene));
        }
    }

    public void browseInitialize() throws Exception {

        try {
            int count = 0;
            String sqlStatement = "SELECT recipeName, minutes, n_steps, n_ingredients, submitted FROM recipe";
            connection = DatabaseConnector.getConnection();
            rs = connection.createStatement().executeQuery(sqlStatement);

            // Add all contents to oblist for storage
            while (rs.next()) {
                oblist.add(new TableModel(rs.getString("recipeName"), rs.getInt("minutes"), rs.getInt("n_steps"), rs.getInt("n_ingredients"), rs.getString("submitted")));
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
