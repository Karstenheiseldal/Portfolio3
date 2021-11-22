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

        this.view.findClasses.setOnAction(e->HandlerPrintStudentClasses(view.studentComB.getValue(),1,view.result));
        view.configure();
    }

    public ObservableList<Integer> getStudents() throws SQLException {
        ArrayList<Integer> students = model.QueryGetStudentsID();
        ObservableList<Integer> studentsNames= FXCollections.observableArrayList(students);
        return studentsNames;
    }

    public void HandlerPrintStudentClasses(Integer StudentID, Integer ClassID, TextArea txtfield){
        txtfield.clear();
        txtfield.appendText("Classes for student");
        try {
            ArrayList<StudentInfo> collectClass = model.QueryforStudents(StudentID, ClassID);

            for (int i = 0; i < collectClass.size(); i++) {
                Integer classes = collectClass.get(i).classId;
                Integer student = collectClass.get(i).StudentId;
                Integer grade = collectClass.get(i).grades;

                txtfield.appendText("\n Student ID: " + student + " Attendended at class ID:" + classes);
                txtfield.appendText(" \n Grade: " +grade);
            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}