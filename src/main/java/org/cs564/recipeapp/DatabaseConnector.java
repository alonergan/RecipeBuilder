package org.cs564.recipeapp;

import java.io.File;
import java.sql.*;

public class DatabaseConnector {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/";
    public static Connection getConnection() throws Exception {
        try {
            String username = "recipeapp";
            String password = "recipeapp";

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url + "recipebuilder", username, password);
            if (connection != null) {
                System.out.println("Connected to database...");
            } else {
                System.out.println("Error connecting to database");
            }

            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Adds new user to mySQL server, generates a new user_id for storage within tables
     * @param username  username 0-64 char
     * @param password  password
     * @return true if successfully added
     */
    public static boolean addUser(Connection connection, String username, String password) throws Exception {
        try {
            // TODO: Add user function
            if (connection == null) {
                System.out.println("Error with connection in addUser");
                return false;
            }
            Statement statement = connection.createStatement();
            String query = "CREATE USER '" + username + "'@'localhost' IDENTIFIED BY '" + password + "';";
            statement.executeUpdate(query);
            query = "GRANT ALL PRIVILEGES ON *.* TO '" + username + "'@'localhost' WITH GRANT OPTION;"; // MAJOR SECURITY ISSUE, SHOULD NOT GIVE ROOT PERMISSIONS (For simplicity ignore this)
            statement.executeUpdate(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Initializes and populates database when first running the application
     * @return
     * @throws Exception
     */
    public static boolean initializeDatabase(File csvPath, String rootUsername, String rootPassword, String username, String password) throws Exception {
        try {
            // Connect to SQL as root and add new User
            Connection connection = DriverManager.getConnection(url, rootUsername, rootPassword);
            if (connection == null) {
                System.out.println("Error connecting to database, is MySQL installed?");
                return false;
            }
            Statement statement = connection.createStatement();
            String query = "CREATE DATABASE recipebuilder";
            statement.executeUpdate(query);

            // Create Tables
                // Recipe
            query = "CREATE TABLE Recipe (" +
                    "recipe_name varchar(255)," +
                    "recipe_id int," +
                    "minutes int," +
                    "n_steps int," +
                    "n_ingredients int," +
                    "submitted date," +
                    "description varchar(2048)," +
                    "avg_rating double DEFAULT 0.0," +
                    "primary key (recipe_id));";
            statement.executeUpdate(query);
                // Tag
            query = "CREATE TABLE Tag (" +
                    "recipe_id int," +
                    "tag_num int," +
                    "tag_name varchar(512)," +
                    "primary key (recipe_id, tag_num));";
            statement.executeUpdate(query);
                // Ingredient
            query = "CREATE TABLE Ingredient (" +
                    "recipe_id int," +
                    "ingredient_num int," +
                    "ingredient_name varchar(255)," +
                    "primary key (recipe_id, ingredient_num));";
            statement.executeUpdate(query);
                // Step
            query = "CREATE TABLE Step (" +
                    "recipe_id int," +
                    "step_num int," +
                    "step_name varchar(2048)," +
                    "primary key (recipe_id, step_num));";
            statement.executeUpdate(query);
                // Review
            query = "CREATE TABLE Review (" +
                    "user_id int," +
                    "recipe_id int," +
                    "submitted date," +
                    "rating int," +
                    "review text(64000)," +
                    "primary key (user_id, recipe_id));";
            statement.executeUpdate(query);
                // inPantry
            query = "CREATE TABLE inPantry (" +
                    "username varchar(64)," +
                    "ingredient_name varchar(256)," +
                    "primary key(username, ingredient_name));";
            statement.executeUpdate(query);
                // isFavorite
            query = "CREATE TABLE isFavorite (" +
                    "username varchar(64)," +
                    "recipe_id int," +
                    "primary key(username, recipe_id));";
            statement.executeUpdate(query);

            // Populate Tables TODO: Test if this works
            File[] files = csvPath.listFiles();
            assert files != null;
                // Ingredients
            query = "BULK INSERT Ingredient" +
                    "FROM '" + files[0].getAbsolutePath() + "'" +
                    "WITH (" +
                    "FIELDTERMINATOR = '|'," +
                    "ROWTERMINATOR = '\r\n')";
            statement.executeUpdate(query);
                // Recipes
            query = "BULK INSERT Recipe" +
                    "FROM '" + files[1].getAbsolutePath() + "'" +
                    "WITH (" +
                    "FIELDTERMINATOR = '|'," +
                    "ROWTERMINATOR = '\r\n')";
            statement.executeUpdate(query);
                // Review
            query = "BULK INSERT Review" +
                    "FROM '" + files[2].getAbsolutePath() + "'" +
                    "WITH (" +
                    "FIELDTERMINATOR = '|'," +
                    "ROWTERMINATOR = '\r\n')";
            statement.executeUpdate(query);
                // Step
            query = "BULK INSERT Step" +
                    "FROM '" + files[3].getAbsolutePath() + "'" +
                    "WITH (" +
                    "FIELDTERMINATOR = '|'," +
                    "ROWTERMINATOR = '\r\n')";
            statement.executeUpdate(query);
                // Tags
            query = "BULK INSERT Tag" +
                    "FROM '" + files[4].getAbsolutePath() + "'" +
                    "WITH (" +
                    "FIELDTERMINATOR = '|'," +
                    "ROWTERMINATOR = '\r\n')";
            statement.executeUpdate(query);

            // Update avg_rating in Recipe
            query = "UPDATE Recipe r JOIN (" +
                    "SELECT recipe_id, AVG(rating) as avg_rating" +
                    "FROM Review v GROUP BY recipe_id" +
                    ") v ON r.recipe_id = v.recipe_id" +
                    "SET r.avg_rating = v.avg_rating;";
            statement.executeUpdate(query);

            // Add new user to server
            if (!addUser(connection, username, password)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        getConnection();
    }
}
