package org.cs564.recipeapp;

import java.util.ArrayList;

/**
 * Class to represent recipe object in database
 */
public class Recipe {
    int id;
    int n_ingredients;
    int n_minutes;
    int n_steps;
    int contributorID;
    String name;
    String dateSubmitted;
    String description;
    ArrayList<String> tags;
    ArrayList<Double> nutrition;
    ArrayList<String> steps;
    ArrayList<String> ingredients;

    public Recipe(int id, int n_ingredients, int n_minutes, int n_steps, int contributorID, String name, String dateSubmitted, String description, ArrayList<String> tags, ArrayList<Double> nutrition, ArrayList<String> steps, ArrayList<String> ingredients) {
        this.id = id;
        this.n_ingredients = n_ingredients;
        this.n_minutes = n_minutes;
        this.n_steps = n_steps;
        this.contributorID = contributorID;
        this.name = name;
        this.dateSubmitted = dateSubmitted;
        this.description = description;
        this.tags = tags;
        this.nutrition = nutrition;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_ingredients() {
        return n_ingredients;
    }

    public void setN_ingredients(int n_ingredients) {
        this.n_ingredients = n_ingredients;
    }

    public int getN_minutes() {
        return n_minutes;
    }

    public void setN_minutes(int n_minutes) {
        this.n_minutes = n_minutes;
    }

    public int getN_steps() {
        return n_steps;
    }

    public void setN_steps(int n_steps) {
        this.n_steps = n_steps;
    }

    public int getContributorID() {
        return contributorID;
    }

    public void setContributorID(int contributorID) {
        this.contributorID = contributorID;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Double> getNutrition() {
        return nutrition;
    }

    public void setNutrition(ArrayList<Double> nutrition) {
        this.nutrition = nutrition;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
