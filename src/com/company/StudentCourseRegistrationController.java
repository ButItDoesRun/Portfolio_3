package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentCourseRegistrationController {
    StudentCourseRegistrationView view;
    StudentCourseRegistrationModel model;

    //constructor
    public StudentCourseRegistrationController
            (StudentCourseRegistrationView v, StudentCourseRegistrationModel m) throws SQLException{
        this.view = v;
        this.model = m;


        //--------------------------------------------------model commands---------------------------------------//
        //establish connection to database (done through the model)
        this.model.connectToDatabase();

        //create a statement
        this.model.CreateStatement();

        //get result-set from query
        //this.model.TestSQLQueryStudents();


        //-----------------------------------------------view commands-----------------------------------------//
        //exit button action
        this.view.ExitBtn.setOnAction(e ->
                {
                    Platform.exit();
                    try{
                        model.closeDatabaseConnection();
                        System.out.println("Connection closed");
                    }catch(Exception b){
                        System.out.println(b.getMessage());
                    }

                });

        //students
        this.view.students = getStudents();


        //combobox configurations executed
        this.view.configureComBContent();

    }


    public ObservableList<String> getStudents() throws SQLException{
        ArrayList<String> students = model.TestSQLQueryStudents();
        ObservableList<String> studentNames = FXCollections.observableArrayList(students);
        return studentNames;
    }

}

