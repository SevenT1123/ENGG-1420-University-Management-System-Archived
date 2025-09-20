package com.example.scenebuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import university.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfilePageController implements Initializable {
    private Student student;

    @FXML
    private Label usernameStudentMainPage;

    @FXML
    private Label userIdStudentMainPage;

    @FXML
    private Label addressStudentMainPage;

    @FXML
    private Label telephoneStudentMainPage;

    @FXML
    private Label thesistTitleStudentMainPage;

    @FXML
    private Label emailStudentMainPage;

    @FXML
    private Label academicLevelStudentMainPage;

    @FXML
    private Label currentSemesterStudentMainPage;

    @FXML
    private Label passwordStudentMainPage;

    @FXML
    private TextField newPasswordFillStudentMainPage;

    public void setUser(Student student) {
        this.student = student;
        displayStudentProfileInfo();
    }

    private void displayStudentProfileInfo() {
        if (student != null) {
            usernameStudentMainPage.setText(student.getName());
            userIdStudentMainPage.setText(student.getUserId());
            addressStudentMainPage.setText(student.getAddress());
            telephoneStudentMainPage.setText(student.getTelephone());
            thesistTitleStudentMainPage.setText(student.getThesisTitle());
            emailStudentMainPage.setText(student.getEmail());
            academicLevelStudentMainPage.setText(student.getAcademicLevel());
            currentSemesterStudentMainPage.setText(student.getCurrentSemester());
            passwordStudentMainPage.setText(student.getPassword());
        }
    }

    public void changePasswordPopUp(ActionEvent event) {
        student.setPassword(newPasswordFillStudentMainPage.getText());
        passwordStudentMainPage.setText(newPasswordFillStudentMainPage.getText());
        newPasswordFillStudentMainPage.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
