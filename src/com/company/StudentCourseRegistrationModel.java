package com.company;

import javafx.scene.control.TextArea;

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

    public ArrayList<CourseInfo> SQLQueryCourseInfo(Integer courseID) throws SQLException{
        ArrayList<CourseInfo> courseInfos = new ArrayList<>();

        String sql ="SELECT studentID AS ID, name AS N " +
                "FROM Student JOIN Grade AS G on Student.studentID = G.stuID, Course AS C " +
                "Where G.couID = C.courseId AND G.couID = ?;";

        //create a prepared-statement
        pstmt = con.prepareStatement(sql);
        //insert parameter
        pstmt.setInt(1, courseID);
        rs = pstmt.executeQuery();

        while(rs != null && rs.next()){
            //get result
            Integer ID = rs.getInt("ID");
            String studentName = rs.getString("N");

            //insert result into class an object
            CourseInfo CI = new CourseInfo(ID,studentName);

            //add object to returnable array
            courseInfos.add(CI);
        }
        return courseInfos;
    }

    public Average SQLQueryCourseAverage(Integer courseID) throws SQLException{
        String sql = "SELECT AVG(grade) AS AVG From Grade Where couID = ?;";

        //create a prepared-statement
        pstmt = con.prepareStatement(sql);
        //insert parameter
        pstmt.setInt(1, courseID);
        //execute query
        rs = pstmt.executeQuery();

        //get result
        Double Average = rs.getDouble("AVG");

        Average AVG = new Average(Average);


        if(AVG != null){
            System.out.println(AVG);
            return AVG;
        }else if(AVG == null){
            AVG = new Average("No current average");
            System.out.println(AVG);
            return AVG;
        }

        return AVG;
    }





}

/*
click a student and get all their courses, and grades. as well as the students average grade
click a course and get all students and the course average grade

 */

class StudentInfo{
    String courseName = null;
    Types grade = null;

    StudentInfo(String couName, Types grad){
        this.courseName = couName;
        this.grade = grad;
    }

}

class CourseInfo{
    Integer studentID = null;
    String studentName = null;

    CourseInfo(Integer stuID, String stuName){
        this.studentID = stuID;
        this.studentName = stuName;
    }

}


class Average<T>{
    T Average = null;

    Average(T AVG){
        this.Average = AVG;
    }
}

