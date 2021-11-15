package com.company;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class StudentCourseRegistrationView {
    GridPane startview;


    //constructor
    public StudentCourseRegistrationView(){
        //creates a new view
        startview = new GridPane();
    }


    //create view
    private void CreateView(){
        //view-configurations
        startview.setMinSize(300,200);
        startview.setPadding(new Insets(10,10,10,10));
        startview.setHgap(5);
        startview.setVgap(5);
    }


    //returns the view - note that the method needs the "asParent()" because a view needs a parent to work
    //therefore what we are doing here is basically typecasting the method into being the Parent
    public Parent asParent(){
        return startview;
    }



}
