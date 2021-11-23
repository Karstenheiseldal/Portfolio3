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

        this.view.findClasses.setOnAction(e->HandlerPrintStudentClasses(view.studentComB.getValue(),3,view.result));
        view.configure();
    }

    public ObservableList<Integer> getStudents() throws SQLException {
        ArrayList<Integer> students = model.QueryGetStudentsID();
        ObservableList<Integer> studentIds= FXCollections.observableArrayList(students);
        return studentIds;
    }

    public void HandlerPrintStudentClasses(Integer StudentID, Integer ClassID, TextArea txtfield){
        txtfield.clear();
        txtfield.appendText("Classes for student");
        try {
            ArrayList<StudentInfo> collectClass = model.QueryforStudents(StudentID, ClassID);


                String classes = collectClass.get(this.view.studentComB.getValue()).className;
                String student = collectClass.get(this.view.studentComB.getValue()).name;
                Integer grade = collectClass.get(this.view.studentComB.getValue()).grades;
                Integer year = collectClass.get(this.view.studentComB.getValue()).classYear;

                txtfield.appendText("Student: " + student + "\n Class: " + classes + " " + year +"\n  Grade: " + grade);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}