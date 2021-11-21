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
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);

            System.out.println("Student ID:" + studentID + "  Name: " + firstname + " " + lastname);

            students.add(firstname);

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

    public ArrayList<String> QueryGetGrades() throws SQLException {
        ArrayList<String> gradeList = new ArrayList<>();
        String sql = "SELECT * from Grades;";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            String grade = rs.getString(1);
            String studentID = rs.getString(2);
            String classID = rs.getString(3);

            System.out.println("Student ID:" + studentID + "  Grade: " + grade + " " + "CLass ID: " + classID);
            gradeList.add(grade);
        }
        return gradeList;
    }

    public void findAverage() throws SQLException {
        String sql = "SELECT AVG(Grade) from Grades;";
        rs = stmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            String average = rs.getString(1);

        }
    }
}
