package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

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
        //courseIDs
        this.view.courseIDs = getCourseIDs();

        //courseInfo button action
        this.view.courseInfo.setOnAction(e -> HandleCourseInfoPrint(view.courseIDComB.getValue(), view.textfield));

        //studentIfo button action
        this.view.studentInfo.setOnAction(e -> HandleStudentInfoPrint(view.studentNameComB.getValue(), view.textfield));


        //combobox configurations executed
        this.view.configureComBContent();

    }


    public ObservableList<String> getStudents() throws SQLException{
        ArrayList<String> students = model.SQLQueryStudents();
        ObservableList<String> studentNames = FXCollections.observableArrayList(students);
        return studentNames;
    }

    public ObservableList<Integer> getCourseIDs() throws SQLException{
        ArrayList<Integer> courses = model.SQLQueryCourseIDs();
        ObservableList<Integer> courseIds = FXCollections.observableArrayList(courses);
        return courseIds;
    }


    public void HandleStudentInfoPrint(String studentName, TextArea textfield){
        //clear textfield
        textfield.clear();

        try{
            if(studentName == null){
                textfield.appendText("Please choose a student");
            }else {
                //headline studentNames from this course
                textfield.appendText(studentName + "'s courses and grades are: \n");

                ArrayList<StudentInfo> SInfo = model.SQLQueryStudentInfo(studentName);
                for (int i = 0; i < SInfo.size(); i++) {
                    textfield.appendText("Course: " + SInfo.get(i).courseName + ", Grade -->" + SInfo.get(i).grade + "\n");
                }
                textfield.appendText("\nGrade Average: " + model.SQLQueryStudentAverage(studentName));
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public void HandleCourseInfoPrint(Integer courseID, TextArea textfield){
        //clear textfield
        textfield.clear();
        //headline studentNames from this course
        textfield.appendText("Students participating in this course: \n");
        try{
            ArrayList<CourseInfo> CInfo = model.SQLQueryCourseInfo(courseID);
            for(int i = 0; i < CInfo.size(); i++){
                textfield.appendText("StudentID: " + CInfo.get(i).studentID
                        + " -----> " + CInfo.get(i).studentName + "\n");
            }
            textfield.appendText("\nCourse Average: " + model.SQLQueryCourseAverage(courseID));

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

