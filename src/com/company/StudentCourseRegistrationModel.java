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

    //this method gets names, id and year for all courses from database
    public ArrayList<Course> SQLQueryCourses() throws SQLException{
        ArrayList<Course> courseIDs = new ArrayList<>();
        //SQL query
        String sql = "SELECT courseId AS Id, courseName AS CN, year AS Y FROM Course";
        //execute sql-query
        rs = stmt.executeQuery(sql);

        while(rs != null && rs.next()){
            //gets sql-result
            Integer id = rs.getInt("Id");
            String cName = rs.getString("CN");
            Integer year = rs.getInt("Y");

            //adds sql-result to the array
            Course courses = new Course(cName, id, year);

            //add object to returnable array
            courseIDs.add(courses);
        }
        return courseIDs;
    }

    //this method gets a students courses and grades from database based the parameter "studentName"
    public ArrayList<StudentInfo> SQLQueryStudentInfo(String studentName) throws SQLException{
        ArrayList<StudentInfo> studentInfos = new ArrayList<>();
        String sql = "SELECT courseName AS CN, IFNULL(grade, 'Not graded') AS G " +
                "FROM Course JOIN Grade AS G2 on Course.courseId = G2.couID, " +
                "Student AS S WHERE G2.stuID = S.studentID AND S.name = ?;";

        //create a prepared-statement
        pstmt = con.prepareStatement(sql);
        //insert parameter
        pstmt.setString(1, studentName);
        rs = pstmt.executeQuery();

        while(rs != null && rs.next()){
            //get result
            String cName = rs.getString("CN");
            String grade = rs.getString("G");

            //insert result into class an object
            StudentInfo SI = new StudentInfo(cName, grade);

            //add object to returnable array
            studentInfos.add(SI);
        }

        return studentInfos;
    }

    //this method gets a students average from database based on the parameter "studentName"
    public String SQLQueryStudentAverage(String studentName) throws SQLException{
        String sql = "SELECT AVG(grade) AS AVG From Grade JOIN Student S on Grade.stuID = S.studentID " +
                "Where s.name = ?;";

        //create a prepared-statement
        pstmt = con.prepareStatement(sql);
        //insert parameter
        pstmt.setString(1, studentName);
        //execute query
        rs = pstmt.executeQuery();

        //get result
        String Average = rs.getString("AVG");

        return Average;
    }

    //this method gets all students attending a course from database based on the parameter "courseID"
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
            CourseInfo CI = new CourseInfo(ID, studentName);

            //add object to returnable array
            courseInfos.add(CI);
        }
        return courseInfos;
    }

    //this method gets a course's average from database based on the parameter "courseID"
    public String SQLQueryCourseAverage(Integer courseID) throws SQLException{
        String sql = "SELECT IFNULL(AVG(grade), 'Not graded') AS Average From Grade Where couID = ?;";

        //create a prepared-statement
        pstmt = con.prepareStatement(sql);
        //insert parameter
        pstmt.setInt(1, courseID);
        //execute query
        rs = pstmt.executeQuery();

        //get result
        String Average = rs.getString("Average");

        return Average;
    }





}

//-----------------------classes for making returnable objects based on SQL-Queries -------------------------//

class Course{
    String courseName = null;
    Integer courseId = null;
    Integer courseYear = null;

    Course(String couName, Integer courseId, Integer couYear){
        this.courseName = couName;
        this.courseId = courseId;
        this.courseYear = couYear;
    }
}

class StudentInfo{
    String courseName = null;
    String grade = null;

    StudentInfo(String couName, String grad){
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

