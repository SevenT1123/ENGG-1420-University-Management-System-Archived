package com.example.scenebuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import university.*;

public class StudentController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane archorPane;

    @FXML
    private BorderPane borderPane;

    private Student student;

    public void setUser(Student student) {
        this.student = student;
    }

    // Log out method
    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/LoginPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toSubjectPage(MouseEvent event) {
        loadPage("/com/example/scenebuild/Student/SubjectPage");
    }

    @FXML
    private void toCoursePage(MouseEvent event) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/CoursePage.fxml"));
            root = loader.load();

            StudentCoursePageController cc = loader.getController();
            cc.setUser(student);

        } catch (IOException e) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        borderPane.setCenter(root);
    }

    @FXML
    private void toEventsPage(MouseEvent event) {
        loadPage("/com/example/scenebuild/Student/EventsPage");
    }

    @FXML
    private void toFacultyPage(MouseEvent event) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/FacultyMembers.fxml"));
            root = loader.load();

            StudentFacultyPageController fc = loader.getController();
            fc.setUser(student);

        } catch (IOException e) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        borderPane.setCenter(root);
    }

    @FXML
    private void toProfilePage(MouseEvent event) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/ProfilePage.fxml"));
            root = loader.load();

            StudentProfilePageController pc = loader.getController();
            pc.setUser(student);

        } catch (IOException e) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        borderPane.setCenter(root);
    }

    private void loadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException e) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}



