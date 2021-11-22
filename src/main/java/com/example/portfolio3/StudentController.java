package com.example.portfolio3;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentViewer view;
    StudentModel model;

    public StudentController(StudentViewer v, StudentModel m) throws SQLException {
        this.view=v;
        this.model=m;
        this.model.connectToStudentData();
        this.model.CreateStatement();


        this.view.students= getStudents();
        this.view.classes = getClasses();

        this.view.findClasses.setOnAction(e-> {
            try {
                PrintClassesByStudentID(view.studentComB.getValue(), view.result);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });



        this.model.QueryGetClasses();
        this.model.QueryGetStudentsID();
        this.model.Queryforstudentclasses(view.studentComB.getValue());

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

    public void PrintClassesByStudentID (String StudentID, TextArea txtArea) throws SQLException{
        txtArea.clear();
        txtArea.appendText("Names of classes: \n");
        ArrayList<StudentInfo> classList = model.Queryforstudentclasses(StudentID);
        for (int i = 0; i < classList.size(); i++) {
            System.out.println(classList.get(i).className);

        }
    }

}