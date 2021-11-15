package com.company;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudentCourseRegistrationView {
    //variables
    GridPane startview;

    //Labels
    Label studentFirstname;

    //buttons
    Button ExitBtn;



    //constructor
    public StudentCourseRegistrationView(){
        //creates a new view
        startview = new GridPane();
        CreateView();
    }


    //create view
    private void CreateView(){
        //view-configurations
        startview.setMinSize(300,200);
        startview.setPadding(new Insets(10,10,10,10));
        startview.setHgap(5);
        startview.setVgap(5);


        //----------------creates view-items-------------------------//
        //labels
        studentFirstname = new Label("First Name");

        //buttons
        ExitBtn = new Button("Exit");

        //----------------adds view-items to view-------------------------//
        //labels
        startview.add(studentFirstname, 1, 1);

        //buttons
        startview.add(ExitBtn, 20,20);

    }


    //returns the view - note that the method needs the "asParent()" because a view needs a parent to work
    //therefore what we are doing here is basically typecasting the method into being the Parent
    public Parent asParent(){
        return startview;
    }



}
