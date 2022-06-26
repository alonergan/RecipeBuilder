package org.cs564.recipeapp;

import java.util.ArrayList;

public class Users {
    static ArrayList<ArrayList<String>> users = new ArrayList<>();

    /**
     * Adds new login to users array and checks for duplicate usernames
     * @param username  username to be added
     * @param password  password for username
     * @return          true if username was added, false otherwise
     */
    public static boolean addLogin(String username, String password) {
        // Check if username exists
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username)) {
                return false;
            }
        }

        // Add username to ArrayList
        ArrayList<String> newUser = new ArrayList<>(2);
        newUser.add(username);
        newUser.add(password);
        users.add(newUser);
        return true;
    }

    /**
     * Checks if given username and password are correct
     * @param username  username to verify
     * @param password  password to verify
     * @return          true if correct false otherwise
     */
    public static boolean verifyLogin(String username, String password) {
        // Check for username in users
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username)) {
                if (user.get(1).equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
