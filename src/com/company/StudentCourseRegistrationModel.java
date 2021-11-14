package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentCourseRegistrationModel {
    Connection con = null;
    String url = null;

    //sets the model to be an url (which in this case is the url for the Java Database Connector (JDBC))
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

}


/*
class SCRInfo{
    Integer studentID = null;

}

 */
