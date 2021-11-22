package com.example.portfolio3;


import com.example.portfolio3.StudentModel;
import com.example.portfolio3.StudentViewer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentViewer view;
    StudentModel model;

    public StudentController(StudentViewer v, StudentModel m) throws SQLException {
        this.view=v;
        this.model=m;
        this.view.exitB.setOnAction(e-> Platform.exit());
        this.view.students= getStudents();
        this.view.classes = getClasses();


        this.model.connectToStudentData();
        this.model.CreateStatement();
        this.model.QueryGetClasses();
        this.model.QueryGetStudentsID();

        view.configure();
    }

    public ObservableList<String> getStudents() throws SQLException {
        ArrayList<String> students= model.QueryGetStudentsID();
        ObservableList<String> StudentNames= FXCollections.observableArrayList(students);
        return StudentNames;
    }

    public ObservableList<String> getClasses() throws SQLException {
        ArrayList<String> classes= model.QueryGetClasses();
        ObservableList<String> classesNames= FXCollections.observableArrayList(classes);
        return classesNames;
    }


    public void gradeButtonHandler (TextArea clicked) throws SQLException {

        try {
            clicked.clear();
            clicked.appendText(" Clicked button");

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

}
