package com.example.scenebuild;

import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import university.Course;
import university.CourseManager;
import university.Student;


import java.io.IOException;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class StudentCoursePageController implements Initializable {
    @FXML
    private TableColumn<Course, Integer> capacityColumn;

    @FXML
    private TableColumn<Course, String> courseCodeColumn;

    @FXML
    private TableColumn<Course, String> courseColumn;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> examDateColumn;

    @FXML
    private TableColumn<Course, String> locationColumn;

    @FXML
    private TableColumn<Course, String> sectionColumn;

    @FXML
    private TableColumn<Course, String> subjectColumn;

    @FXML
    private TableColumn<Course, String> teacherColumn;

    @FXML
    private TableColumn<Course, String> timeColumn;

    private ArrayList<String> courses = new ArrayList<>();

    private CourseManager courseManager = new CourseManager();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("StudentCoursePageController initialized!");
        setUpColumns();
        loadCoursesIntoTable();
    }

    private void setUpColumns(){
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("sectionNumber"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureTime"));
        examDateColumn.setCellValueFactory(new PropertyValueFactory<>("finalExamDateTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    }




    private Student student;

    private void loadCoursesIntoTable() {
        courseManager.loadCoursesFromFile(); // Loads from Courses.txt
        List<Course> courseList = courseManager.getCourses();
        ObservableList<Course> observableCourses = FXCollections.observableArrayList(courseList);
        courseTable.setItems(observableCourses);
    }

    public void setUser(Student student) {
        this.student = student;
        printCouses(student.getSubjectsRegistered());
    }

    public void viewEnrolledCourses(ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/EnrolledCourses.fxml"));
        root = fxmlLoader.load();
        EnrolledCoursesController controller = fxmlLoader.getController();
        controller.setStudent(student);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void printCouses(String input) {
        String[] courses = input.split(", ");
        for (String course : courses) {
            System.out.println(course);
        }
    }
    }


