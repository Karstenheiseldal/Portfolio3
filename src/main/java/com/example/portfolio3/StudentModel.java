package com.example.portfolio3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentModel {
    Connection conn = null;
    String url = null;
    Statement stmt = null;
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
            String studentID = rs.getString(1);

            students.add(studentID);

        }
        return students;
    }

    public ArrayList<String> QueryGetClasses() throws SQLException {
        ArrayList<String> classes = new ArrayList<>();
        String sql = "SELECT * from Classes;";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {

            String name = rs.getString(4);


            classes.add(name);

        }

        return classes;
    }

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
    }
