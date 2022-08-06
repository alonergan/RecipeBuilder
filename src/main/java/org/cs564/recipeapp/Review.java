package org.cs564.recipeapp;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.w3c.dom.Text;

/**
 * Class to represent recipe review in database
 */
public class Review {
    int recipeID;
    int rating;
    String date;
    TextArea reviewArea;

    public Review(int userID, int recipeID, int rating, String date, String review) {
        this.recipeID = recipeID;
        this.rating = rating;
        this.date = date;
        this.reviewArea = new TextArea(review);
        reviewArea.setWrapText(true);
        reviewArea.setEditable(false);
        reviewArea.setPrefRowCount(5);
    }
    public TextArea getReviewArea() {return reviewArea;};

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

    public String getReviewText() { return reviewArea.getText(); }

    public void setReviewText(String review) {
        this.reviewArea.setText(review);
    }
}
