package org.cs564.recipeapp;

public class TableModel {
    String name;
    int minutes;
    int steps;
    int numIngredients;
    String date;

    public TableModel(String name, int minutes, int steps, int numIngredients, String date) {
        this.name = name;
        this.minutes = minutes;
        this.steps = steps;
        this.numIngredients = numIngredients;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getNumIngredients() {
        return numIngredients;
    }

    public void setNumIngredients(int numIngredients) {
        this.numIngredients = numIngredients;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
