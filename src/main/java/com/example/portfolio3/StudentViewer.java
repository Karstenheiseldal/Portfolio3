package com.example.portfolio3;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StudentViewer {
        GridPane startview;


        Label studentLbl=new Label("Select student:");
        Label classLbl=new Label("Select class");
        Button findClasses;
        TextArea result = new TextArea("List of classes");


        ComboBox<String> studentComB;
        public ObservableList<String> students;

        ComboBox<String> classComB;
        public ObservableList<String> classes;



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
            startview.add(classLbl,1,2);
            startview.add(result,1,6, 15,7);

            findClasses = new Button("Classes");
            startview.add(findClasses,2,50);

            studentComB=new ComboBox<>();
            startview.add(studentComB,2,1);

            classComB = new ComboBox<>();
            startview.add(classComB,2,2);




            //clicked = new TextArea();
            //startview.add(clicked,0,3,4,2);

        }

        public void configure(){
            studentComB.setItems(students);
            studentComB.getSelectionModel().selectFirst();

            classComB.setItems(classes);
            classComB.getSelectionModel().selectFirst();

        }

        public Parent asParent(){
            return  startview;
        }
    }