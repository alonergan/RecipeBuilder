package org.cs564.recipeapp;

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
    public boolean addUser(String username, String password) throws Exception {
        try {
            // TODO: Add user function
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
    public boolean initializeDatabase() throws Exception {
        try {
            // Connect to SQL as root and add new User
            Connection connection = DriverManager.getConnection(url, "root", "root"); // TODO: Figure out common root user/password
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
            // Populate Tables TODO: Find way to populate tables with data (local infile? but how to get paths?)
                // Recipe
                // Tag
                // Ingredient
                // Step
                // Review
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
