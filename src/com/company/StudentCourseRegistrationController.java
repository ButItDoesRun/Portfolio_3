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


        //--------------------------------------------------model controls---------------------------------------//
        //establish connection to database (done through the model)
        this.model.connectToDatabase();

        //create a statement
        this.model.CreateStatement();


        //-----------------------------------------------view controls-----------------------------------------//
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

        //get observableList-content for the students
        this.view.students = getStudents();
        //get observableList-content for the courseIds
        this.view.courseIDs = getCourseIDs();

        //courseInfo button action
        this.view.courseInfo.setOnAction(e -> HandleCourseInfoPrint(view.courseIDComB.getValue(), view.courseTextfield));

        //studentInfo button action
        this.view.studentInfo.setOnAction(e -> HandleStudentInfoPrint(view.studentNameComB.getValue(), view.studentTextfield));


        //combobox configurations executed
        this.view.configureComBContent();

    }


    public ObservableList<String> getStudents() throws SQLException{
        ArrayList<String> students = model.SQLQueryStudents();
        ObservableList<String> studentNames = FXCollections.observableArrayList(students);
        return studentNames;
    }

    public ObservableList<Integer> getCourseIDs() throws SQLException{
        ArrayList<Integer> courses = new ArrayList<>();
        ArrayList<Course> coursesObject = model.SQLQueryCourses();
        for(int i = 0; i < coursesObject.size(); i++){
            Integer id = coursesObject.get(i).courseId;
            courses.add(id);
        }
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

        try{
            ArrayList<Course> cList = model.SQLQueryCourses();
            for(int c = 0; c < cList.size(); c++){
                if(courseID == cList.get(c).courseId){
                    textfield.appendText("Course: " + cList.get(c).courseName + " " + cList.get(c).courseYear + "\n");
                }

            }

            textfield.appendText("Students participating in this course: \n");

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

