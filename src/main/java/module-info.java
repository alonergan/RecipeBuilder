module org.cs564.recipeapp {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.cs564.recipeapp to javafx.fxml;
    exports org.cs564.recipeapp;
}