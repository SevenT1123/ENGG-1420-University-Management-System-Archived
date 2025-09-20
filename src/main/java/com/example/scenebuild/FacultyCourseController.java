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
import javafx.scene.control.cell.PropertyValueFactory;
import university.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FacultyCourseController implements Initializable {

    @FXML private TableColumn<Course, Integer> capacityColumn;
    @FXML private TableColumn<Course, String> courseCodeColumn;
    @FXML private TableColumn<Course, String> courseColumn;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> examDateColumn;
    @FXML private TableColumn<Course, String> locationColumn;
    @FXML private TableColumn<Course, String> sectionColumn;
    @FXML private TableColumn<Course, String> subjectColumn;
    @FXML private TableColumn<Course, String> teacherColumn;
    @FXML private TableColumn<Course, String> timeColumn;

    private Faculty faculty;
    private final CourseManager courseManager = new CourseManager();

    // Sets the logged-in faculty user and loads assigned courses
    public void setUser(Faculty faculty) {
        this.faculty = faculty;
        loadAssignedCourses(); // Populate table with this faculty’s courses
    }

    // Called when the FXML is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpColumns(); // Setup table column bindings
    }

    // Binds table columns to Course class properties
    private void setUpColumns() {
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("sectionNumber"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureTime"));
        examDateColumn.setCellValueFactory(new PropertyValueFactory<>("finalExamDateTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    // Loads only the courses assigned to the logged-in faculty
    private void loadAssignedCourses() {
        if (faculty == null) {
            System.out.println("Faculty is null.");
            return;
        }

        // Raw comma-separated string of assigned course names
        String assignedCoursesRaw = faculty.getCoursesOffered();
        System.out.println("Raw assigned courses string: " + assignedCoursesRaw);

        // Convert string to list of course names
        List<String> assignedCourseNames = List.of(assignedCoursesRaw.split(",\\s*"));
        System.out.println("Parsed assigned course names: " + assignedCourseNames);

        // Load all courses from file and filter by assigned course names
        List<Course> allCourses = courseManager.loadAndReturnCoursesFromFile();
        System.out.println("Total courses loaded: " + allCourses.size());

        List<Course> filtered = allCourses.stream()
                .filter(course -> assignedCourseNames.contains(course.getCourseName()))
                .collect(Collectors.toList());

        System.out.println("Matched/filtered courses: " + filtered.size());
        for (Course c : filtered) {
            System.out.println("Matched course: " + c.getCourseName());
        }

        // Populate table with matched courses
        ObservableList<Course> data = FXCollections.observableArrayList(filtered);
        courseTable.setItems(data);
    }
}
