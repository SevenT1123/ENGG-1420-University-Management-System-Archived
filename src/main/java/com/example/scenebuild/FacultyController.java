package com.example.scenebuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import university.*;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacultyController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane archorPane;

    @FXML
    private BorderPane borderPane;

    private Faculty faculty;

    public void setUser(Faculty faculty) {
        this.faculty = faculty;
    }

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/LoginPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void toSubjectPage(MouseEvent event) {
        try {
            Parent root = loadFXML("/com/example/scenebuild/Faculty/SubjectsPage.fxml");
            FacultySubjectController controller = getController();
            controller.setUser(faculty);
            borderPane.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void toCoursePage(MouseEvent event) {
        try {
            Parent root = loadFXML("/com/example/scenebuild/Faculty/CoursePage.fxml");
            FacultyCourseController controller = getController();
            controller.setUser(faculty);
            borderPane.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void toEventsPage(MouseEvent event) {
        try {
            Parent root = loadFXML("/com/example/scenebuild/Faculty/EventManager.fxml");
            FacultyEventsController controller = getController();
            controller.setUser(faculty);
            borderPane.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void toEnrolledStudents(MouseEvent event) {
        try {
            Parent root = loadFXML("/com/example/scenebuild/Faculty/EnrolledStudentsPage.fxml");
            FacultyEnrolledStudentsController controller = getController();
            controller.setUser(faculty);
            borderPane.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void toProfilePage(MouseEvent event) {
        try {
            Parent root = loadFXML("/com/example/scenebuild/Faculty/ProfilePage.fxml");
            FacultyProfilePageController controller = getController();
            controller.setUser(faculty);
            borderPane.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Helper method to load an FXML file and return the Parent node.
     */
    private FXMLLoader currentLoader;
    private Parent loadFXML(String path) throws IOException {
        System.out.println("Attempting to load: " + path);
        URL fxmlUrl = getClass().getResource(path);
        System.out.println("Resolved URL: " + fxmlUrl);

        if (fxmlUrl == null) {
            throw new IOException("FXML file not found at: " + path);
        }

        currentLoader = new FXMLLoader(fxmlUrl);
        return currentLoader.load();
    }

    /**
     * Gets the most recent controller from the last loadFXML call.
     */
    @SuppressWarnings("unchecked")
    private <T> T getController() {
        return currentLoader.getController();
    }
}
