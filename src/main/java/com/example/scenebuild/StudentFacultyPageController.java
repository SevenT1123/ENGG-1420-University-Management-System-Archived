package com.example.scenebuild;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import university.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentFacultyPageController implements Initializable {
    private Student student;

    // Faculty page variables
    @FXML
    private TableView<Faculty> viewFaculty;

    @FXML
    private TableColumn<Faculty, String> facultyNameViewStudentMainPage;

    @FXML
    private TableColumn<Faculty, String> facultyEmailViewStudentMainPage;

    @FXML
    private TableColumn<Faculty, String> facultyOLViewStudentMainPage;

    @FXML
    private TableColumn<Faculty, String> facultyRIViewStudentMainPage;

    public void setUser(Student student) {
        this.student = student;
        updateTable(this.student);
        viewFacultyProfiles(student);
    }

    private void updateTable(Student student) {
        facultyNameViewStudentMainPage.setCellValueFactory(new PropertyValueFactory<>("name"));
        facultyEmailViewStudentMainPage.setCellValueFactory(new PropertyValueFactory<>("email"));
        facultyRIViewStudentMainPage.setCellValueFactory(new PropertyValueFactory<>("researchInterest"));
        facultyOLViewStudentMainPage.setCellValueFactory(new PropertyValueFactory<>("officeLocation"));

        viewFaculty.setItems(list(student));
    }

    private ObservableList<Faculty> list(Student student) {
        LoadFile fileload = new LoadFile();
        ObservableList<Faculty> facultyList = FXCollections.observableArrayList();
        ArrayList<String> teacherList = fileload.fetchAll("TextData/Courses.txt", student.getSubjectsRegistered(), "Teacher Name");

        for (String teacher : teacherList) {
            String name = fileload.fetchAnything("TextData/Faculty.txt", teacher, "Name");
            String email = fileload.fetchAnything1("TextData/Faculty.txt", teacher,"Name", "Email");
            String researchInterest = fileload.fetchAnything1("TextData/Faculty.txt", teacher, "Name","Research Interest");
            String officeLocation = fileload.fetchAnything1("TextData/Faculty.txt", teacher, "Name","Office Location");

            facultyList.add(new Faculty("",name, "", researchInterest, email, officeLocation, "", ""));
        }

        return facultyList;
    }

    public void viewFacultyProfiles(Student student) {
        LoadFile fileload = new LoadFile();
        ArrayList<String> teacherList = fileload.fetchAll("TextData/Courses.txt", student.getSubjectsRegistered(), "Teacher Name");
        for (String teacher : teacherList) {
            System.out.println(fileload.fetchAnything("TextData/Faculty.txt", teacher, "Name"));
            System.out.println(fileload.fetchAnything1("TextData/Faculty.txt", teacher,"Name", "Email"));
            System.out.println(fileload.fetchAnything1("TextData/Faculty.txt", teacher, "Name","Research Interest"));
            System.out.println(fileload.fetchAnything1("TextData/Faculty.txt", teacher, "Name","Office Location"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
