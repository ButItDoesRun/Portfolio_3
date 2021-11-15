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
    TextArea textfield;

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
        textfield = new TextArea();

        //----------------adds view-items to view-------------------------//
        //labels
        startview.add(chooseStudent, 1, 1);
        startview.add(chooseCourse, 1, 2);

        //buttons
        startview.add(ExitBtn, 20,20);
        startview.add(studentInfo, 1,6);
        startview.add(courseInfo, 1, 7);

        //combobox
        startview.add(studentNameComB,2,1);
        startview.add(courseIDComB, 2,2);

        //textfield
        startview.add(textfield,1,10, 15,10);

    }

    public void configureComBContent(){
        //sets obslist "students" as comB content
        studentNameComB.setItems(students);
        //makes sure that the list is shown in the comB from the first item, and the rest
        studentNameComB.getSelectionModel().selectFirst();

        //courses
        courseIDComB.setItems(courseIDs);
        courseIDComB.getSelectionModel().selectFirst();

    }


    //returns the view - note that the method needs the "asParent()" because a view needs a parent to work
    //therefore what we are doing here is basically typecasting the method into being the Parent
    public Parent asParent(){
        return startview;
    }



}
