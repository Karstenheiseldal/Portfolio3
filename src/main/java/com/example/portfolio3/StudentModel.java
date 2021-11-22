package com.example.portfolio3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentModel {
    Connection conn = null;
    String url = null;
    Statement stmt = null;
    PreparedStatement pstmt=null;
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

    public ArrayList<String> QueryGetClasses() throws SQLException {
        ArrayList<String> classes = new ArrayList<>();
        String sql = "SELECT * from Classes;";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {

            String name = rs.getString(4) + " " + rs.getString(2);
            classes.add(name);

        }
        return classes;
    }



   /*public void PstmtRetrieveClassByStudent() throws SQLException{
        System.out.println("Which student?");
        Scanner scanner = new Scanner(System.in);
        String StudentID = scanner.nextLine();

        String sql= "SELECT ClassID FROM Grades WHERE StudentID = ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,StudentID);
        rs=pstmt.executeQuery();
    }


    /*public ArrayList<String> QueryGetGradesByStudentID(String StudentID, String ClassID)  throws SQLException{

        ArrayList<String> gradesById = new ArrayList<>();
        String sql = "SELECT grade from Grades WHERE StudentID = " + StudentID + ";";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {

            String grades ="Grade: "+ rs.getString(1) + " Class: " + rs.getString(3);
            gradesById.add(grades);
            System.out.println(rs.getString(1));

        }
        return gradesById;
    }*/

    /*public void showGrade(String studentID, String classID) throws SQLException {
        String sql = "SELECT grade from Grades WHERE studentID = " + studentID + "AND classID = " + classID + ";";
        rs = stmt.executeQuery(sql);
        ArrayList<String> grades = new ArrayList<>();
        while (rs != null && rs.next()) {

            String thevalue = rs.getString(1);

            grades.add(thevalue);

        }
    }*/

        public void findAverage() throws SQLException {
        String sql = "SELECT AVG(Grade) from Grades;";
        rs = stmt.executeQuery(sql);
        String average = rs.getString(1);
        }

    public ArrayList<StudentInfo> Queryforstudentclasses(String StudentID) throws SQLException{

        ArrayList<StudentInfo> studentinf=new ArrayList<>();

        String sql="SELECT ClassID FROM Grades WHERE studentID = ?; ";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,StudentID);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            String grade=rs.getString(1);
            String studentid=rs.getString(2);
            String classid=rs.getString(3);
            StudentInfo  s = new StudentInfo(studentid, classid, grade);
            studentinf.add(s);
        }
        return studentinf;
    }
}

class StudentInfo{
    String StudentId=null;
    String className=null;
    String grades=null;

    StudentInfo(String StudentId,String classID, String grades){
        this.StudentId=StudentId;
        this.className=classID;
        this.grades=grades;
    }
}


