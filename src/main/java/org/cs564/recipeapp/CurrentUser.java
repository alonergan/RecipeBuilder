package org.cs564.recipeapp;

import java.sql.Connection;

/**
 * Holds current user data for connection to SQL server - NOT PART OF USERS class
 */
public final class CurrentUser {
    String username;
    String password;
    Connection connection;

    private final static CurrentUser INSTANCE = new CurrentUser();

    private CurrentUser() {}

    private CurrentUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
