package com.example.scenebuild;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import university.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Admin admin;

    @FXML
    private AnchorPane archorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button logOutButtonFacultyMainMenu;

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/LoginPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void toCoursePage(MouseEvent event) {

    }

    @FXML
    void toEnrolledStudents(MouseEvent event) {

    }

    @FXML
    void toEventsPage(MouseEvent event) {

    }

    @FXML
    void toProfilePage(MouseEvent event) {

    }

    @FXML
    void toSubjectPage(MouseEvent event) {

    }

    public void setUser(Admin admin) {
        this.admin = admin;
    }



}
