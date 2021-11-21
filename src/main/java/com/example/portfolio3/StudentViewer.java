package com.example.portfolio3;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudentViewer {
        GridPane startview;
        Label studentLbl;

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


            studentLbl=new Label("Select student:");
            startview.add(studentLbl,1,1);

            Button ExitBtn=new Button("Exit");
            startview.add(ExitBtn,4,5);

            studentComB=new ComboBox<>();
            startview.add(studentComB,2,1);

            classComB = new ComboBox<>();
            startview.add(classComB,2,3);

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