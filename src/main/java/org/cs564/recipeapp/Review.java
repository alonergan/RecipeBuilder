package org.cs564.recipeapp;

/**
 * Class to represent recipe review in database
 */
public class Review {
    int recipeID;
    int rating;
    String date;
    String review;

    public Review(int userID, int recipeID, int rating, String date, String review) {
        this.recipeID = recipeID;
        this.rating = rating;
        this.date = date;
        this.review = review;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
