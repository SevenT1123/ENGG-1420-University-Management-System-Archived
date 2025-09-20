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
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class    EnrolledCoursesController implements Initializable  {
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

    private Stage stage;
    private Scene scene;
    private Parent root;


    private final CourseManager courseManager = new CourseManager();
    private Student student;

    public void setStudent(Student student){
        this.student = student;
        System.out.println("EnrolledCoursesController: setStudent called with " + student.getName());
        loadEnrolledCourses();




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupColumns();
    }

    private void setupColumns(){
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

    private void loadEnrolledCourses(){
        courseManager.loadCoursesFromFile();
        List<Course> allCourses = courseManager.getCourses();

        if (student == null) return;

        List<String> enrolledSubjects = Arrays.asList(student.getSubjectsRegistered().split(",\\s*"));

        List<Course> enrolledCourses = allCourses.stream()
                .filter(course -> enrolledSubjects.contains(course.getSubjectName()))
                .collect(Collectors.toList());

        ObservableList<Course> observableCourses = FXCollections.observableArrayList(enrolledCourses);
        courseTable.setItems(observableCourses);
    }
    public void StudentMainPage(ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/StudentMainPage.fxml"));
        root = fxmlLoader.load();
        StudentController controller = fxmlLoader.getController();
        controller.setUser(student);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
