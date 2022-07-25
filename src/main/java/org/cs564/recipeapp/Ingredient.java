package org.cs564.recipeapp;

public class Ingredient {
    private int recipeID;
    private String name;

    public Ingredient(int recipeID, String name) {
        this.recipeID = recipeID;
        this.name = name;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
}
