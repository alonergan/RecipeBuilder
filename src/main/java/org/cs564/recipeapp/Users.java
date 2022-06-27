package org.cs564.recipeapp;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Users {

    /**
     * Adds new login to users array and checks for duplicate usernames
     * @param username  username to be added
     * @param password  password for username
     * @return          true if username was added, false otherwise
     */
    public static boolean addLogin(String username, String password) {
        // Check if username exists
        try {
            File file = new File("src/main/resources/userData.txt");
            Scanner scnr = new Scanner(file);
            scnr.nextLine(); // Skip header
            String line;
            String[] userData;

            // Check all usernames for duplicates
            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                userData = line.split(",");
                if (userData[0].equals(username)) {
                    return false;
                }
            }

            // Add login
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);
            writer.println(username + "," + password);
            writer.close();
            bw.close();
            fw.close();
            scnr.close();
            return true;

        } catch (IOException e) {
            Alert ioError = new Alert(Alert.AlertType.ERROR);
            ioError.setContentText("IOException: Could not find user data");
            ioError.showAndWait();
            e.printStackTrace();
            return false;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if given username and password are correct
     * @param username  username to verify
     * @param password  password to verify
     * @return          true if correct false otherwise
     */
    public static boolean verifyLogin(String username, String password) {
        try {
            File file = new File("src/main/resources/userData.txt");
            Scanner scnr = new Scanner(file);
            scnr.nextLine(); // Skip header
            String line;
            String[] userData;

            // Find username
            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                userData = line.split(",");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            Alert ioError = new Alert(Alert.AlertType.ERROR);
            ioError.setContentText("IOException: Could not find user data");
            ioError.showAndWait();
            e.printStackTrace();
            return false;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
