package org.cs564.recipeapp;

public class Step {
    private int recipe_id;
    private int step_num;
    private String name;

    public Step(int recipe_id, int step_num, String name) {
        this.recipe_id = recipe_id;
        this.step_num = step_num;
        this.name = name;
    }

    // For displaying step in a tableView (no recipe_id needed)
    public Step(int step_num, String name) {
        this.step_num = step_num;
        this.name = name;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getStep_num() {
        return step_num;
    }

    public void setStep_num(int step_num) {
        this.step_num = step_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
