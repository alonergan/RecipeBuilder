package org.cs564.recipeapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to test recipe application, but mostly to test if database is returning correct results
 */
public class Tests {
    public static Connection connection;
    public static ResultSet rs;

    public static void main(String[] args) throws Exception {
        runAllTests();
    }


    public static void runAllTests() throws Exception {
        connection = DatabaseConnector.getConnection();
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    /**
     * Tests if output from database is correct for query with 1 table
     * @return test result
     */
    public static boolean test1() throws SQLException {
        String actual = "default, never got connection";
        String expected = "carrot   tuna salad, 111111";
        String query = "SELECT recipe_name, recipe_id FROM Recipe WHERE recipe_id = 111111;";
        if (connection != null) {
            rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                actual = rs.getString("recipe_name") + ", " + rs.getInt("recipe_id");
            }
            if (actual.equalsIgnoreCase(expected)) {
                System.out.println("Test 1 Passed");
                return true;
            }
        }
        System.out.println("Test 1 Failed");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        return false;
    }

    /**
     * Tests if output is correct for select query with 2 tables
     * @return test result
     */
    public static boolean test2() throws SQLException {
        String actual = "default, never got connection";
        String expected = "111111, canned tuna";
        String query = "SELECT r.recipe_id, i.ingredient_name FROM Recipe r INNER JOIN Ingredient i ON r.recipe_id = i.recipe_id WHERE r.recipe_id = 111111 AND i.ingredient_num = 2;";
        if (connection != null) {
            rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                actual = rs.getInt("recipe_id") + ", " + rs.getString("ingredient_name");
            }
            if (actual.equalsIgnoreCase(expected)) {
                System.out.println("Test 2 Passed");
                return true;
            }
        }
        System.out.println("Test 2 Failed");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        return false;
    }

    /**
     * Tests if query returns correct results when finding average rating
     * @return
     */
    public static boolean test3() throws SQLException {
        String actual = "default, never got connection";
        String expected = "4.5";
        String query = "SELECT AVG(rating) rating FROM Review WHERE recipe_id = 111111 GROUP BY recipe_id;";
        if (connection != null) {
            rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                actual = String.valueOf(rs.getDouble("rating"));
            }
            if (actual.equalsIgnoreCase(expected)) {
                System.out.println("Test 3 Passed");
                return true;
            }
        }
        System.out.println("Test 3 Failed");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        return false;
    }



     /**
     * Tests if query returns correct results when finding recipe time
     * @return
     */
    public static boolean test4() {
        int actual = 0;
        String query = "SELECT * FROM Recipe r WHERE r.minutes < 30;";
        if (connection != null) {
            try {
            rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                actual = rs.getInt("minutes");
                if (actual >= 30) {
                    System.out.println("Test 4 Failed: result contains value greater than or eqaul to 30");
                    return false;
                }
            }
            System.out.println("Test 4 Passed");
            return true;
        }
            catch(Exception e){
                System.out.println("Test 4 Failed:");
                e.printStackTrace();
            }
        }
        return false;
    }
    /** 
     * Tests if query returns at least 30 results when searching for recipes with common ingredients like eggs
     * @return
     */
    public static boolean test5() {
        String query = "SELECT r.* FROM Recipe r INNER JOIN Ingredient i ON r.recipe_id = i.recipe_id WHERE i.ingredient_name LIKE '%eggs%';";
        int iter = 0;
        if (connection != null) {
            try {
            rs = connection.createStatement().executeQuery(query);
            while (rs.next() && iter <30) {
                iter++;
            }
            if (iter == 30) {
                System.out.print("Test 5 Passed");
                return true;
            }
            else {
                System.out.print("Test 5 Failed: Should have at least 30 results, but only has:" + iter);
                return false;
            }
        }
            catch(Exception e){
                System.out.println("Test 5 Failed:");
                e.printStackTrace();
            }
        }
        return false;
    }
}
