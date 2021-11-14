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
    StudentCourseRegistrationModel(String url){
        this.url = url;
    }

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


    public ArrayList<String> TestSQLQueryStudents() throws SQLException{
        ArrayList<String> students = new ArrayList<>();
        System.out.println("got here");
        java.lang.String sql = "SELECT firstName FROM Teacher";
        System.out.println("got here2");
        rs = stmt.executeQuery(sql);
        System.out.println("got here3");

        while(rs != null && rs.next()){
            java.lang.String name = rs.getString(1);
            System.out.println(name);

            //adds sql-results to the array
            students.add(name);
        }
        return students;
    }



}


/*
class SCRInfo{
    Integer studentID = null;

}

 */