package com.example.portfolio3;

import java.nio.charset.IllegalCharsetNameException;
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

    public ArrayList<Integer> QueryGetStudentsID() throws SQLException {
        ArrayList<Integer> students = new ArrayList<>();
        String sql = "SELECT * from Student;";
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            Integer id = rs.getInt(1);
            students.add(id);
        }
        return students;
    }
    public ArrayList<StudentInfo> QueryforStudents(Integer StudentID, Integer ClassID) throws SQLException{

        ArrayList<StudentInfo> studentinf=new ArrayList<>();
        String sql="SELECT * FROM Grades\n" +
                "    JOIN Classes C on ?\n" +
                "    JOIN Student S on ? = Grades.StudentID;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,StudentID);
        pstmt.setInt(2,ClassID);

        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            Integer st=rs.getInt(2);
            Integer cl=rs.getInt(3);
            Integer gr=rs.getInt(1);
            Integer cly = rs.getInt(5);
            String name = rs.getString(9) + " " + rs.getString(10);
            String clName = rs.getString(7);
            System.out.println(st+ " got grade "+gr+ " at class"+ cl);
            StudentInfo s=new StudentInfo(st,cl,gr,name,clName,cly);
            studentinf.add(s);
        }
        return studentinf;
    }
}

class StudentInfo{
    Integer StudentId=null;
    Integer classId=null;
    Integer grades=null;
    Integer classYear=null;

    String name = null;
    String className = null;

    StudentInfo(Integer StudentId,Integer classID, Integer grades, String name, String className, Integer classYear){
        this.StudentId=StudentId;
        this.classId=classID;
        this.grades=grades;

        this.name = name;
        this.className = className;
        this.classYear = classYear;
    }
}


