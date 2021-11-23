package com.example.portfolio3;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StudentViewer {
        GridPane startview; //a new gridPane is made
        Label studentLbl=new Label("Select student ID:"); //Creates text on a new label for students
        Label classesLbl=new Label("Select course: "); //Creates label for classes

        Button findStudent; //A button with the name findStudent
        Button findClass; //Same for class
        ComboBox<Integer> studentComB; //Creates the menulist with students
        ComboBox<Integer> classesComB; //Same for classes choice


        TextArea result = new TextArea("Please select student ID"); //Creates the result area with the specified text
        public ObservableList<Integer> students; //Student and Class observable list to the javaFX
        public ObservableList<Integer> classes; //

        public StudentViewer(){ //Studentiewer constructor. A ew gridpane and then uses the createView, which is the method below.
            startview=new GridPane();
            CreateView();
        }

        private void CreateView(){ //This imbodies the view, where the v's are defining the window size.
            startview.setMinSize(300,200);
            startview.setPadding( new Insets(10,10,10,10)); //Padding and gaps
            startview.setHgap(1); //
            startview.setVgap(5);

            startview.add(studentLbl,1,1); //Adds the studentlabel
            studentComB=new ComboBox<>();
            startview.add(studentComB,2,1); //Adding the Combobox for student


            findStudent = new Button("Student Info");
            startview.add(findStudent,3,1);  //Button as mentioned, Gets a position in the view


            startview.add(classesLbl,1,2); //Adds the classes text on the UI with classeslabel
            classesComB=new ComboBox<>();
            startview.add(classesComB,2,2); //Same with ComboBox
            findClass = new Button("Course average");
            startview.add(findClass,3,2); //Placement of the new button

            startview.add(result,1,6, 15,7); //Adding the textarea last
        }

        public void configure(){
            studentComB.setItems(students); // Configure method for the the view. Inserts students to their studentComboBox
            studentComB.getSelectionModel().selectFirst(); //The combobox is now showing the first element
            classesComB.setItems(classes); //Inserts classes to their combobox
            classesComB.getSelectionModel().selectFirst(); //The combobox is now showing the first element
        }

        public Parent asParent(){
            return  startview;
        } //Startview is Parent
}