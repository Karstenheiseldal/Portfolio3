package com.example.portfolio3;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StudentViewer {
        GridPane startview;
        Label studentLbl=new Label("Select student ID:");
        Label classesLbl=new Label("Select course: ");

        Button findStudent;
        Button findClass;
        ComboBox<Integer> studentComB;
        ComboBox<Integer> classesComB;


        TextArea result = new TextArea("Please select student ID");
        public ObservableList<Integer> students;
        public ObservableList<Integer> classes;

        public StudentViewer(){
            startview=new GridPane();
            CreateView();
        }

        private void CreateView(){
            startview.setMinSize(300,200);
            startview.setPadding( new Insets(10,10,10,10));
            startview.setHgap(1);
            startview.setVgap(5);

            startview.add(studentLbl,1,1);
            studentComB=new ComboBox<>();
            startview.add(studentComB,2,1);


            findStudent = new Button("Student Info");
            startview.add(findStudent,3,1);


            startview.add(classesLbl,1,2);
            classesComB=new ComboBox<>();
            startview.add(classesComB,2,2);
            findClass = new Button("Course average");
            startview.add(findClass,3,2);

            startview.add(result,1,6, 15,7);
        }

        public void configure(){
            studentComB.setItems(students);
            studentComB.getSelectionModel().selectFirst();
            classesComB.setItems(classes);
            classesComB.getSelectionModel().selectFirst();
        }

        public Parent asParent(){
            return  startview;
        }
}