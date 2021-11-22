package com.company;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class StudentCourseRegistrationView {
    //variables
    GridPane startview;

    //Labels
    Label chooseStudent;
    Label chooseCourse;

    //buttons
    Button ExitBtn;
    Button studentInfo;
    Button courseInfo;

    //Comboboxes
    ComboBox<String> studentNameComB;
    ComboBox<Integer> courseIDComB;

    //textfield
    TextArea studentTextfield;
    TextArea courseTextfield;

    //observable lists
    ObservableList<String> students;
    ObservableList<Integer> courseIDs;



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
        chooseStudent = new Label("Choose a student");
        chooseCourse = new Label("Choose a course");

        //buttons
        ExitBtn = new Button("Exit");
        studentInfo = new Button("Show student-information");
        courseInfo = new Button("Show course-information");

        //combobox
        studentNameComB = new ComboBox<>();
        courseIDComB = new ComboBox<>();

        //textfield
        studentTextfield = new TextArea();
        courseTextfield = new TextArea();

        //----------------adds view-items to view-------------------------//
        //labels
        startview.add(chooseStudent, 1, 1);
        startview.add(chooseCourse, 1, 12);

        //buttons
        startview.add(ExitBtn, 21,20);
        startview.add(studentInfo, 20,3);
        startview.add(courseInfo, 20, 14);

        //combobox
        startview.add(studentNameComB,2,1);
        startview.add(courseIDComB, 2,12);

        //textfield
        startview.add(studentTextfield,1,4, 20,2);
        startview.add(courseTextfield, 1, 15, 20, 2);

    }

    public void configureComBContent(){
        //sets obslist "students" as comB content
        studentNameComB.setItems(students);
        //makes sure that the list is shown in the comB from the first item, and the rest
        //studentNameComB.getSelectionModel().selectFirst();
        studentNameComB.getSelectionModel();

        //courses
        courseIDComB.setItems(courseIDs);
        courseIDComB.getSelectionModel().selectFirst();

    }


    //returns the view typecast as Parent
    public Parent asParent(){
        return startview;
    }



}
