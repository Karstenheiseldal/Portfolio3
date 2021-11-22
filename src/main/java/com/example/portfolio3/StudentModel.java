package com.example.portfolio3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentModel {
    Connection conn = null;
    String url = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    StudentModel(String url) {
        this.url = url;
    }

    public void connectToStudentData() throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public void closeStudentDataConnection() throws SQLException {
        if (conn != null)
            conn.close();
    }

    public void CreateStatement() throws SQLException {
        this.stmt = conn.createStatement();
    }

    public ArrayList<String> QueryGetStudentsID() throws SQLException {
        ArrayList<String> students = new ArrayList<>();
        String sql = "SELECT * from Student;";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            String Name = rs.getString(2) + " " + rs.getString(3);
            students.add(Name);
        }
        return students;
    }
    public ArrayList<StudentInfo> QueryforStudents(Integer StudentID, Integer ClassID) throws SQLException{
        // Scanner scanner = new Scanner(System.in);
        ArrayList<StudentInfo> studentinf=new ArrayList<>();
        //  System.out.println("Which Station do you want to depart from:");
        //  String stStation= scanner.nextLine();
        //   System.out.println("Which Station do you want to go to?");
        //   String endStation=scanner.nextLine();
        //  System.out.println("At what time would you like to depart?");
        //  float departuretime = scanner.nextFloat();
        String sql="SELECT * FROM Grades JOIN Classes C on C.ClassID = Grades.ClassID AND ? = Grades.StudentID AND ? = Grades.ClassID ;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,StudentID);
        pstmt.setInt(2,ClassID);

        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            Integer st=rs.getInt(2);
            Integer cl=rs.getInt(3);
            Integer gr=rs.getInt(1);

            System.out.println(st+ " got grade "+gr+ " at class"+ cl);
            StudentInfo s=new StudentInfo(st, cl, gr);
            studentinf.add(s);
        }
        return studentinf;
    }
}

class StudentInfo{
    Integer StudentId=null;
    Integer className=null;
    Integer grades=null;

    StudentInfo(Integer StudentId,Integer classID, Integer grades){
        this.StudentId=StudentId;
        this.className=classID;
        this.grades=grades;
    }
}


