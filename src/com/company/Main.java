package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.*;

public class Main extends Application {

    //my javaFX stage
    public void start(Stage primaryStage){
        //use url from database-location and fix all \\ to /
        String url ="jdbc:sqlite:C:/Users/s/Documents/RUC/Til OneDrive/Datalogi/" +
                "Fagmodulskursus 2 - Software Development/Assignments - Portfolio/" +
                "Portfolio 3/Portfolio_3/src/StudentsCourseRegistration.db";


        //make new view
        StudentCourseRegistrationView SCRView = new StudentCourseRegistrationView();

        //make new model
        StudentCourseRegistrationModel SCRModel = new StudentCourseRegistrationModel(url);

        //make variable name for new controller
        StudentCourseRegistrationController SCRController = null;

        try{
            //make a new controller
            SCRController =new StudentCourseRegistrationController(SCRView, SCRModel);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        //create scene
        primaryStage.setScene(new Scene(SCRView.asParent(), 600, 475));
        primaryStage.setTitle("Student Course Registration");
        primaryStage.show();

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

    //launching my code from main
    public static void main(String[] args) {
        launch(args);
    }
}
