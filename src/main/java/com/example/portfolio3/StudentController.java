package com.example.portfolio3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

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
        this.view.classes = getClasses();
        this.view.findStudent.setOnAction(e->HandlerPrintStudent(view.studentComB.getValue(),view.classesComB.getValue(),view.result));
        view.configure();
        this.view.findClass.setOnAction(e-> HandlerPrintClass(view.classesComB.getValue(), view.result));
    }

    public ObservableList<Integer> getStudents() throws SQLException {
        ArrayList<Integer> students = model.QueryGetStudentsID();
        ObservableList<Integer> studentIds= FXCollections.observableArrayList(students);
        return studentIds;
    }

    public ObservableList<Integer> getClasses() throws SQLException {
        ArrayList<Integer> classes = model.QueryGetClassesID();
        ObservableList<Integer> classID= FXCollections.observableArrayList(classes);
        return classID;
    }

    public void HandlerPrintStudent(Integer StudentID, Integer ClassID, TextArea txtfield){
        txtfield.clear();

        try {
            ArrayList<StudentInfo> collectClass = model.QueryforStudents(StudentID, ClassID);
            Integer average = model.findStudentAverage(StudentID);

                String classes = collectClass.get(StudentID).className;
                String student = collectClass.get(StudentID).name;
                Integer grade = collectClass.get(StudentID).grades;
                Integer year = collectClass.get(StudentID).classYear;

                txtfield.appendText("Student: " + student + "\nClass: " + classes + " " + year +"\nGrade: " + grade + "\nAverage " + average);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void HandlerPrintClass(Integer ClassID, TextArea txtfield){
        txtfield.clear();
        try {
            Integer average = model.findClassAverage(ClassID);

                txtfield.appendText("Class average: " + average);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}