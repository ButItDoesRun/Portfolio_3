package com.company;

import java.sql.SQLException;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        //use url from database-location and fix all \\ to /
        String url ="jdbc:sqlite:C:/Users/s/Documents/RUC/Til OneDrive/Datalogi/" +
                "Fagmodulskursus 2 - Software Development/Assignments - Portfolio/" +
                "Portfolio 3/Portfolio_3/src/StudentsCourseRegistration.db";
        StudentCourseRegistrationModel SCRModel = new StudentCourseRegistrationModel(url);

        try{

            //establish connection
            SCRModel.connectToDatabase();

            //create a statement
            SCRModel.CreateStatement();

            System.out.println("here i am");

            //get resultset from query
            SCRModel.TestSQLQueryStudents();
            System.out.println("here i am2");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        /*
        finally {
            try{
                //con.close();
                SCRModel.closeDatabaseConnection();
            }catch(SQLException e2){
                System.out.println(e2.getMessage());
            }
        }

         */

    }
}
