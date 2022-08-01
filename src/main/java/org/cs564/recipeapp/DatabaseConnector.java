package org.cs564.recipeapp;

import java.sql.*;

public class DatabaseConnector {

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/recipebuilder";
            String username = "recipeapp";
            String password = "recipeapp";

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database...");
            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
//    public static boolean init() throws Exception {
//        Connection connection = getConnection();
//
//        return false;
//    }
    public static void main(String[] args) throws Exception {
        getConnection();
    }
}
