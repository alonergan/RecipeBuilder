package org.cs564.recipeapp;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Adds new login to users array and checks for duplicate usernames
     * @param username  username to be added
     * @param password  password for username
     * @return          true if username was added, false otherwise
     */
    public static boolean addLogin(String username, String password) {
        // Check if username exists
        try {
            File file = new File("src/main/resources/org/cs564/recipeapp/data/logins.txt");
            Scanner scnr = new Scanner(file);
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
            File file = new File("src/main/resources/org/cs564/recipeapp/data/logins.txt");
            Scanner scnr = new Scanner(file);
            String line;
            String[] userData;

            // Find username
            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                userData = line.split(",");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    scnr.close();
                    return true;
                }
            }
            scnr.close();
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

    /**
     * Delete the user
     * TODO: add verification; have user input password for 'security'
     * But also running out of time and the account is just to keep track of their ingredients on-hand, who cares
     */
    public static void deleteAccount(String username) {
        try {
            File oldFile = new File("src/main/resources/org/cs564/recipeapp/data/logins.txt");
            File newFile = new File("src/main/resources/org/cs564/recipeapp/data/tmp.txt");
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner scnr = new Scanner(oldFile);
            String line;
            String[] tokens;

            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                tokens = line.split(",");
                if (!tokens[0].equalsIgnoreCase(username)) {
                    // Push line to print writer
                    pw.println(line);
                }
            }

            scnr.close();
            pw.flush();
            pw.close();
            boolean deleted = oldFile.delete();
            if (deleted) {System.out.println("Deleted File");}
            File dump = new File("src/main/resources/org/cs564/recipeapp/data/logins.txt");
            newFile.renameTo(dump);
        } catch (IOException e) {
            Alert ioError = new Alert(Alert.AlertType.ERROR);
            ioError.setContentText("IOException: Could not find user data");
            ioError.showAndWait();
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
