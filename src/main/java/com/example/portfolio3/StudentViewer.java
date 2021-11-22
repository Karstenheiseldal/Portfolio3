package com.example.portfolio3;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StudentViewer {
        GridPane startview;
        Label studentLbl=new Label("Select student:");

        Button findClasses;
        ComboBox<Integer> studentComB;

        TextArea result = new TextArea("Classes and Grades for");
        public ObservableList<Integer> students;

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
            startview.add(result,1,6, 15,7);

            findClasses = new Button("Classes");
            startview.add(findClasses,2,50);

            studentComB=new ComboBox<>();
            startview.add(studentComB,2,1);

        }

        public void configure(){
            studentComB.setItems(students);
            studentComB.getSelectionModel().selectFirst();
        }

        public Parent asParent(){
            return  startview;
        }
}