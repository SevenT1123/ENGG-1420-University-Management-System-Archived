package com.example.scenebuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import university.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FacultyProfilePageController implements Initializable {
    private Faculty faculty;

    @FXML
    private Button askingChangePassword;

    @FXML
    private Label coFacultyMainPage;

    @FXML
    private Label emailFacultyMainPage;

    @FXML
    private Label olFacultyMainPage;

    @FXML
    private Label passwordFacultyMainPage;

    @FXML
    private Label riFacultyMainPage;

    @FXML
    private Label userIdFacultyMainPage;

    @FXML
    private Label usernameFacultyMainPage;

    @FXML
    private TextField newPasswordFillFacultyMainPage;

    public void setUser(Faculty faculty) {
        this.faculty = faculty;
        displayFacultyProfileInfo();
    }

    private void displayFacultyProfileInfo() {
        if(faculty != null) {
            usernameFacultyMainPage.setText(faculty.getName());
            userIdFacultyMainPage.setText(faculty.getUserId());
            riFacultyMainPage.setText(faculty.getResearchInterest());
            olFacultyMainPage.setText(faculty.getOfficeLocation());
            emailFacultyMainPage.setText(faculty.getEmail());
            coFacultyMainPage.setText(faculty.getCoursesOffered());
        }
    }

    @FXML
    void changePasswordPopUp(ActionEvent event) {
        faculty.setPassword(newPasswordFillFacultyMainPage.getText());
        passwordFacultyMainPage.setText(newPasswordFillFacultyMainPage.getText());
        newPasswordFillFacultyMainPage.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
