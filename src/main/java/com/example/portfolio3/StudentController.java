package com.example.portfolio3;

import javafx.collections.FXCollections; //Imported Library from JavaFX
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.SQLException; //Imported Library from SQL, Java FX generic arrayLists
import java.util.ArrayList;

public class StudentController {  //The studentcontroller, operator for the view elements
    StudentViewer view; //There is going to be a studentViewer with the name view
    StudentModel model; //There is also going to be a model.

    public StudentController(StudentViewer v, StudentModel m) throws SQLException { //The controller specified in this constructor
        this.view = v;
        this.model = m;
        this.model.connectToStudentData(); //Connect model to database
        this.model.CreateStatement(); //model creates a statement to the database
        this.model.QueryGetStudentsID(); //model gets the studentid from the database with prepared statements.
        this.view.students = getStudents();  //The views arrayList students is the same as the arraylist returned in the getStudents method.
        this.view.classes = getClasses(); //The view arrayList for classes is the the arrayList returned in the getClasses method
        this.view.findStudent.setOnAction(e->HandlerPrintStudent(view.studentComB.getValue(),view.classesComB.getValue(),view.result)); //button action to print the student values
        view.configure();  //starts the configure method from the studentviewer
        this.view.findClass.setOnAction(e-> HandlerPrintClass(view.classesComB.getValue(), view.result)); //button who gets the class average.
    } //Constructor finish

    public ObservableList<Integer> getStudents() throws SQLException { // //observableList for student comboboxes
        ArrayList<Integer> students = model.QueryGetStudentsID(); //To get the arrayList from database quiery
        ObservableList<Integer> studentIds= FXCollections.observableArrayList(students); //The students arrayList is made to an obserableList.
        return studentIds; // returns the observable list content
    }

    public ObservableList<Integer> getClasses() throws SQLException { //same method with the classes observationlist. Using the models ability to retrieve data and collect the findings in the arrayList..
        ArrayList<Integer> classes = model.QueryGetClassesName(); //Uses the data extracting method from the database through a prep statement.
        ObservableList<Integer> classID= FXCollections.observableArrayList(classes); //This is been converted to an Observable list for the view.
        return classID; //Returns the ObservableList.
    }

    public void HandlerPrintStudent(Integer StudentID, Integer ClassID, TextArea txtfield) { //The handler print student print out the student information. With the use of the object from studentInfo class
        txtfield.clear(); //The textfield is always starting clear

        try {
            ArrayList<StudentInfo> collectStudents = model.QueryStudents(StudentID, ClassID); //Adds an generic arraylist with the type of Studeninfo, which is a blueprint of what is related to the students for future purposes.
            Integer average = model.findStudentAverage(StudentID); //Makes the finding average method with the model.

            String className = collectStudents.get(StudentID).className;
            String student = collectStudents.get(StudentID).name;      //Gets the student String from the lists student gives the of the name of the list
            Integer grade = collectStudents.get(StudentID).grades; //same with grade
            Integer year = collectStudents.get(StudentID).classYear; //Same with year
            txtfield.appendText("Student: " + student + "\nCourse: " + className + " " + year + "\nGrade: " + grade + "\nAverage " + average); //The textfields text
        }
            catch(SQLException e ){
                System.out.println(e.getMessage());
            }
        }

    public void HandlerPrintClass(Integer ClassID, TextArea txtfield){ //Handler to print an average from the ClassID
        txtfield.clear();
        try {
            ArrayList<Integer> collectClass =model.QueryGetClassesName();
            Integer average = model.findClassAverage(ClassID);
            txtfield.appendText("Class average: " + average );
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}