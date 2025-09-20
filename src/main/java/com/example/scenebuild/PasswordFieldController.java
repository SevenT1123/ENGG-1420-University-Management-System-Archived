package com.example.scenebuild;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import university.*;

// This class manages LoginPage.fxml
public class PasswordFieldController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button logInButtonLoginPage;

    @FXML
    private PasswordField passwordFieldLoginPage;

    @FXML
    private ToggleButton showPasswordButtonLoginPage;

    @FXML
    private Label shownPasswordLoginPage;

    @FXML
    private Button signUpButtonLoginPage;

    @FXML
    private TextField usernameLoginPage;

    @FXML
    void passwordFieldKeyTypedLoginPage(KeyEvent event) {
        shownPasswordLoginPage.textProperty().bind(Bindings.concat(passwordFieldLoginPage.getText()));
    }

    @FXML
    void usernameKeyTypedLoginPage(KeyEvent event) {

    }

    @FXML
    void toggleButton(ActionEvent event) {
        if(showPasswordButtonLoginPage.isSelected()) {
            shownPasswordLoginPage.setVisible(true);
            shownPasswordLoginPage.textProperty().bind(Bindings.concat(passwordFieldLoginPage.getText()));
            showPasswordButtonLoginPage.setText("Hide");
        } else {
            shownPasswordLoginPage.setVisible(false);
            showPasswordButtonLoginPage.setText("Show");
        }
    }

    public void validateLogin(ActionEvent event) {
        String userId = usernameLoginPage.getText();
        String password = passwordFieldLoginPage.getText();

        User user = authenticateUser(userId, password);

        if (user != null) {

            char roleIndicator = userId.charAt(0);

            try {
                FXMLLoader loader;
                Parent root;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                switch (roleIndicator) {
                    case 'A':
                        loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Admin/AdminMainPage.fxml"));
                        root = loader.load();

                        AdminController adminController = loader.getController();
                        adminController.setUser((Admin) user);

                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    case 'F':
                        loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Faculty/FacultyMainPage.fxml"));
                        root = loader.load();

                        FacultyController facultyController = loader.getController();
                        facultyController.setUser((Faculty) user);

                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    case 'S':
                        loader = new FXMLLoader(getClass().getResource("/com/example/scenebuild/Student/StudentMainPage.fxml"));
                        root = loader.load();

                        StudentController studentController = loader.getController();
                        studentController.setUser((Student) user);

                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    default:
                        System.out.println("Invalid role detected.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid login");
        }
    }

    private static User authenticateUser(String userId, String password) {

        char roleIndicator = userId.charAt(0);
        // Replace with the path to the file needed
        String fileName = "";

        switch (roleIndicator) {

            case 'A':
                fileName = "TextData/Administrator.txt"; //access admin user and pass
                break;

            case 'S':
                fileName = "TextData/Student.txt";//access student user and pass
                break;

            case 'F':
                fileName = "TextData/Faculty.txt";//etc
                break;

            default:
                System.out.println("Invalid role indicator.");
                break;
        }
        //      default -> throw new IllegalArgumentException("Unknown role indicator!");

        // Read user credentials from file
        LoadFile system = new LoadFile();

        if (password.equals(system.ID_FetchThing(fileName, userId, "Password"))) {
            System.out.println("Valid Login");
        } else{
            System.out.println("INVALID");
            return null;
        }

        String[] obj = system.fetchRow(fileName, userId); //loads an array from text file containing all attributes for a given object

        switch (roleIndicator) {
            case 'A':
                return new Admin(obj[0],obj[1], obj[2], obj[3]);

            case 'S':
                return new Student(obj[0],obj[1], obj[2], obj[3], obj[4], obj[5], obj[6], obj[7], obj[8], obj[9], obj[10], obj[11], "Tuition", "Grades");

            case 'F':
                return new Faculty(obj[0],obj[1], obj[2], obj[3], obj[4], obj[5], obj[6], password);

            default:
                throw new IllegalArgumentException("Unknown role indicator!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shownPasswordLoginPage.setVisible(false);
    }
}