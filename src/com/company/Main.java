package com.company;

import java.sql.SQLException;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String url ="jdbc:sqlite:C:/Users/s/Documents/RUC/Til OneDrive/Datalogi/Fagmodulskursus 2 - Software Development/Forelæsning 16 (d. 01-11-2021) - Database retrievals and manipulations using SQL/SQLExercises/src/SQLExerciseTrain.db";
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
