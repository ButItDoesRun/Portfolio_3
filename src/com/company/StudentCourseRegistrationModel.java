package com.company;

import java.sql.*;
import java.util.ArrayList;

public class StudentCourseRegistrationModel {
    Connection con = null;
    String url = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //sets the model to be an url (which in this case is the url for the Java Database Connector (JDBC))
    //constructor
    StudentCourseRegistrationModel(String url){this.url = url;}

    //method for connecting to the database
    public void connectToDatabase() throws SQLException {
        con = DriverManager.getConnection(url);
    }

    //method for closing database connection
    public void closeDatabaseConnection() throws SQLException{
        if(con != null) {
            con.close();
        }
    }


    public void CreateStatement() throws SQLException{
        this.stmt = con.createStatement();
    }



    //this method gets all studentNames from database
    public ArrayList<String> SQLQueryStudents() throws SQLException{
        ArrayList<String> students = new ArrayList<>();
        //SQL query
        String sql = "SELECT name FROM Student";
        //execute sql-query
        rs = stmt.executeQuery(sql);

        while(rs != null && rs.next()){
            String name = rs.getString(1);
            //adds sql-result to the array
            students.add(name);
        }
        return students;
    }

    //this method gets all courseIDs from database
    public ArrayList<Integer> SQLQueryCourseIDs() throws SQLException{
        ArrayList<Integer> courseIDs = new ArrayList<>();
        //SQL query
        String sql = "SELECT courseId FROM Course";
        //execute sql-query
        rs = stmt.executeQuery(sql);

        while(rs != null && rs.next()){
            //gets sql-result
            Integer id = rs.getInt(1);
            //adds sql-result to the array
            courseIDs.add(id);
        }
        return courseIDs;
    }





}

/*
click a student and get all their courses, and grades. as well as the students average grade
click a course and get all students and their average grade

 */

class StudentInfo{
    Integer studentID = null;

}

class courseInfo{
    Integer studentID = null;
    String studentName = null;
    Integer studentAverage = null;

    courseInfo(Integer stuID, String stuName, Integer stuAvg){
        this.studentID = stuID;
        this.studentName = stuName;
        this.studentAverage = stuAvg;
    }

}

