package org.cs564.recipeapp;

import java.sql.Connection;

/**
 * Holds a User Object for passing login information between scenes
 */
public final class UserHolder {
    private User user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}
    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}
