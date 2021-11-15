package com.company;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudentCourseRegistrationView {
    //variables
    GridPane startview;

    //Labels
    Label studentName;

    //buttons
    Button ExitBtn;

    //Combobox
    ComboBox<String> studentNameComB;



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
        studentName = new Label("Student");

        //buttons
        ExitBtn = new Button("Exit");

        //combobox
        studentNameComB = new ComboBox<>();

        //----------------adds view-items to view-------------------------//
        //labels
        startview.add(studentName, 1, 1);

        //buttons
        startview.add(ExitBtn, 20,20);

        //combobox
        startview.add(studentNameComB,2,1);

    }

    public void configureComBContent(){

    }


    //returns the view - note that the method needs the "asParent()" because a view needs a parent to work
    //therefore what we are doing here is basically typecasting the method into being the Parent
    public Parent asParent(){
        return startview;
    }



}
