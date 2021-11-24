package com.example.portfolio3;
import java.sql.*;
import java.util.ArrayList;


public class StudentModel { //The student model is made in this class.
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
    } //Sets the connection to the database

    public void closeStudentDataConnection() throws SQLException {
        if (conn != null)
            conn.close();
    } //closes the connection

    public void CreateStatement() throws SQLException { //Creates a statement to the database
        this.stmt = conn.createStatement();
    }

    public ArrayList<Integer> QueryGetStudentsID() throws SQLException { //Gets the student ID from the database and added to an arraylist through executeQuery
        ArrayList<Integer> students = new ArrayList<>();
        String sql = "SELECT * from Student;";
        rs = stmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            Integer id = rs.getInt(1);
            students.add(id); //adds the id Integer to the list while there still is an element in the list and is not null.
        }
        return students; //Returns the arraylist, its content is retrieved from the database
    }

    public ArrayList<Integer> QueryGetClassesName() throws SQLException { //gets the classes ID to calculate the average for the class. makes a new arrayList with the infomation similarily as the students arraylist the data is retrieved from the query.
                ArrayList<Integer> classesID = new ArrayList<>();
                String sql = "SELECT * from Classes;";
                rs = stmt.executeQuery(sql);
                while (rs != null && rs.next()) {
                    Integer classID = rs.getInt(1);
                    classesID.add(classID);
                }
                return classesID;
    }
    public ArrayList<StudentInfo> QueryStudents(Integer StudentID, Integer ClassID) throws SQLException{ //ArrayList for information to the StudentInfo object.
        ArrayList<StudentInfo> studentinf=new ArrayList<>();
        String sql="SELECT * FROM Grades JOIN Student S on ? = Grades.StudentID JOIN Classes C on Grades.ClassID = C.ClassID;"; //match on the StudentID in grades
        pstmt=conn.prepareStatement(sql); //The statement gets sent to the database;
        pstmt.setInt(1, StudentID); //To fill inn the first questionmark in the SQL statement.
        rs=pstmt.executeQuery(); //Executes the prepared statement.
        while(rs!=null && rs.next()){
            Integer st=rs.getInt(2);
            String cl=rs.getString(3);
            Integer gr=rs.getInt(1);
            Integer cly = rs.getInt(9);
            String name = rs.getString(5) + " " + rs.getString(6);
            String clName = rs.getString(11);
            //System.out.println(st+ " got grade "+gr+ " at class"+ cl);
            StudentInfo s=new StudentInfo(st,cl,gr,name,clName,cly);
            studentinf.add(s);
        } //Collects the data in arrayList
        return studentinf; //returns the data
    }

    public Integer findStudentAverage(Integer StudentID) throws SQLException { //Finding the average grade for student
        String sql = "SELECT AVG(Grade) from Grades WHERE StudentID = ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,StudentID);
        rs=pstmt.executeQuery();
        Integer average = rs.getInt(1);
        return average;
    }

    public Integer findClassAverage(Integer ClassID) throws SQLException { //Finding the grade average selected by classID
        String sql = "SELECT AVG(Grade) from Grades WHERE ClassID = ?;"; //the statement string in SQL
        pstmt=conn.prepareStatement(sql); //Adding the prepared statement to the connection
        pstmt.setInt(1,ClassID); //Sets a variable to the prepared statement.
        rs=pstmt.executeQuery(); //Executes
        Integer average = rs.getInt(1); //Assign average to variable
        return average; //return the variable
    }
}

class StudentInfo{ //student blueprint
    Integer StudentId; //sets the relevant values that makes a student
    String classId;
    Integer grades;
    Integer classYear;
    String name;
    String className;

    StudentInfo(Integer StudentId,String classID, Integer grades, String name, String className, Integer classYear){ //constructor of the object
        this.StudentId=StudentId;  //Assigns variables
        this.classId=classID;
        this.grades=grades;
        this.name = name;
        this.className = className;
        this.classYear = classYear;
    }
}