package com.example.portfolio3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application { //Starts the application
    public void start(Stage primaryStage) { //Setting stage

        String url = "jdbc:sqlite:C:/Users/karst/Databases/StudentDB.db"; //Database URL
        StudentViewer view = new StudentViewer(); //View for Java FX
        StudentModel model = new StudentModel(url); //The student model
        StudentController control = null; //The Controller of the makes everything happen
        try { //Try catch for SQL Exception.
            control = new StudentController(view, model);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        primaryStage.setTitle("Student OS"); //Setting Window TItle
        primaryStage.setScene(new Scene(view.asParent(), 600, 475)); //SetS scene for Java FX
        primaryStage.show(); //The show method from Java FX
    }

    public static void main(String[] args){
        launch(args);  } //Launches window
}