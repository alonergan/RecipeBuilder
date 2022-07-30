package org.cs564.recipeapp;

public class Ingredient {
    private int recipe_id;
    private int ingredient_num;
    private String name;

    public Ingredient(int recipe_id, int ingredient_num, String name) {
        this.recipe_id = recipe_id;
        this.ingredient_num = ingredient_num;
        this.name = name;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getIngredient_num() {
        return ingredient_num;
    }

    public void setIngredient_num(int ingredient_num) {
        this.ingredient_num = ingredient_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
