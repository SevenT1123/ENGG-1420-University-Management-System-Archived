package com.example.scenebuild;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import university.*;

import java.util.ArrayList;

import static java.util.Collections.list;

public class FacultyEnrolledStudentsController {
    private Faculty faculty;

    @FXML
    private TableColumn<Student, String> studentIdFacultyMainPage;

    @FXML
    private TableColumn<Student, String> studentNameFacultyMainPage;

    @FXML
    private TableView<Student> viewEnrolledStudents;

    public void setUser(Faculty faculty) {
        this.faculty = faculty;
        updateTable(faculty);
        viewStudentInformation(faculty);
    }

    private void updateTable(Faculty faculty) {
        studentIdFacultyMainPage.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameFacultyMainPage.setCellValueFactory(new PropertyValueFactory<>("name"));

        viewEnrolledStudents.setItems(list(faculty));
    }

    private ObservableList<Student> list(Faculty faculty) {
        LoadFile fileload = new LoadFile();
        ObservableList<Student> enrolledStudentList = FXCollections.observableArrayList();
        ArrayList<String> CoursesInformation = fileload.fetchAll("TextData/Courses.txt", faculty.getCoursesOffered(), "Subject Code");

        ArrayList<String> ID = fileload.fetchAll("TextData/Student.txt", CoursesInformation.get(0), "Student ID");
        ArrayList<String> Name = fileload.fetchAll("TextData/Student.txt", CoursesInformation.get(0), "Name");

        for (int i = 0; i < Name.size(); i++){
            String studentId = ID.get(i);
            String name = Name.get(i);

            enrolledStudentList.add(new Student(studentId, name, "", "", "", "", "", "", "", "", "", "", "", ""));
        }

        return enrolledStudentList;
    }

    public void viewStudentInformation(Faculty faculty) {
        LoadFile fileload = new LoadFile();
        System.out.println("This is all of the Courses you are teaching!");
        System.out.println(faculty.getCoursesOffered());

        ArrayList<String> coursesInfo = fileload.fetchAll("TextData/Courses.txt", faculty.getCoursesOffered(), "Subject Code");

        if (coursesInfo.isEmpty()) {
            System.out.println("⚠️ You are not assigned to any courses.");
            return;
        }

        System.out.println("These are all the students that are enrolled in your courses:");

        ArrayList<String> names = fileload.fetchAll("TextData/Student.txt", coursesInfo.get(0), "Name");
        ArrayList<String> ids = fileload.fetchAll("TextData/Student.txt", coursesInfo.get(0), "Student ID");

        for (int i = 0; i < names.size(); i++) {
            System.out.println(i + ". " + names.get(i));
            System.out.println("ID: " + ids.get(i));
            System.out.println("Course Code: " + coursesInfo.get(0));
        }
    }


}
