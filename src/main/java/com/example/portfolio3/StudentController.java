package com.example.portfolio3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;


import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentViewer view;
    StudentModel model;

    public StudentController(StudentViewer v, StudentModel m) throws SQLException {
        this.view = v;
        this.model = m;
        this.model.connectToStudentData();

        this.model.CreateStatement();
        this.model.QueryGetStudentsID();
        this.view.students = getStudents();

        view.configure();
    }

    public ObservableList<String> getStudents() throws SQLException {
        ArrayList<String> students = model.QueryGetStudentsID();
        ObservableList<String> studentsNames= FXCollections.observableArrayList(students);
        return studentsNames;
    }

    public void HandlerPrintStudentClasses(Integer StudentID, Integer ClassID, TextArea txtfield){
        txtfield.clear();
        txtfield.appendText("Classes for student");
        try {
            ArrayList<StudentInfo> collectClass = model.QueryforStudents(StudentID, ClassID);

            for (int i = 0; i < collectClass.size(); i++) {
                Integer classes = collectClass.get(i).className;
                Integer student = collectClass.get(i).StudentId;

                txtfield.appendText(i + ";" + collectClass.get(i).className + ": " + student);
            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}