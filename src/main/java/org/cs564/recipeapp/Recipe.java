package org.cs564.recipeapp;

/**
 * Class to represent recipe object in database
 */
public class Recipe {
    String name;
    int minutes;
    int n_steps;
    int n_ingredients;
    String dateSubmitted;
    int recipe_id;
    String description;

    double rating;

    // Main constructor
    public Recipe(String name, int minutes, int n_steps, int n_ingredients, String dateSubmitted, int recipe_id, String description, double rating) {
        this.recipe_id = recipe_id;
        this.n_ingredients = n_ingredients;
        this.minutes = minutes;
        this.n_steps = n_steps;
        this.name = name;
        this.dateSubmitted = dateSubmitted;
        this.description = description;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return recipe_id;
    }

    public void setId(int id) {
        this.recipe_id = id;
    }

    public int getN_ingredients() {
        return n_ingredients;
    }

    public void setN_ingredients(int n_ingredients) {
        this.n_ingredients = n_ingredients;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getN_steps() {
        return n_steps;
    }

    public void setN_steps(int n_steps) {
        this.n_steps = n_steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
