package com.company;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudentCourseRegistrationView {
    //variables
    GridPane startview;

    //Labels
    Label studentFirstname;

    //buttons



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


        //creates new labels
        studentFirstname = new Label("First Name");

        //adds new labels to view
        startview.add(studentFirstname, 1, 1);



    }


    //returns the view - note that the method needs the "asParent()" because a view needs a parent to work
    //therefore what we are doing here is basically typecasting the method into being the Parent
    public Parent asParent(){
        return startview;
    }



}
