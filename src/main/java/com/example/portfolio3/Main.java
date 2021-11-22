package com.example.portfolio3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    public void start(Stage primaryStage) {

        String url = "jdbc:sqlite:C:/Users/karst/Databases/StudentDB.db";
        StudentViewer view = new StudentViewer();
        StudentModel model = new StudentModel(url);
        StudentController control = null;

        try {
            control = new StudentController(view, model);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        primaryStage.setTitle("Student OS");
        primaryStage.setScene(new Scene(view.asParent(), 600, 475));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);  }
}



/*public class Main {

    public static void main(String[] args) {

        String url="jdbc:sqlite:C:/Users/liner/TrainSQL.db";
        TrainModel TDB=new TrainModel(url);
        try {
   //         conn = DriverManager.getConnection(url);
            TDB.connectToTrainData();
//            stmt= conn.createStatement();
            TDB.CreateStatement();
 //           rs=stmt.executeQuery(sql);
            TDB.SQLQueryStationNames();
 //           while(rs!=null && rs.next()){
 //               String name=rs.getString(1);
 //               System.out.println(name);
  //          }
           // TDB.PstmtRetrieveDeparturesforStation();
         //   TDB.QueryforDepartures();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
                try {
                    TDB.closeTrainDataConnection();
                }catch (SQLException e2){
                    System.out.println(e2.getMessage());

            }
        }
    }
}
*/