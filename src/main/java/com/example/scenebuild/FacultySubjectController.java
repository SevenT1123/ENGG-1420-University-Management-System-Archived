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
import university.*;

import java.io.IOException;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FacultySubjectController implements Initializable {

    private final SubjectManager subjectManager = new SubjectManager();

    @FXML
    private TableColumn<Subject, String> subjectCodeColumn;

    @FXML
    private TableColumn<Subject, String> subjectNameColumn;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TextField searchField;

    private ObservableList<Subject> masterSubjectList;

    private Faculty faculty;

    // Set faculty user passed from FacultyController
    public void setUser(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpColumns();         // Prepare table columns
        loadSubjectsIntoTable(); // Load subject data from file

        // Attach listener to search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSubjects(newValue);
        });
    }

    // Configure table columns to match Subject class properties
    private void setUpColumns() {
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
    }

    // Loads all subjects into the table from file
    private void loadSubjectsIntoTable() {
        List<Subject> subjects = subjectManager.loadSubjectsFromFile();
        masterSubjectList = FXCollections.observableArrayList(subjects);
        subjectTable.setItems(masterSubjectList); // Initial full list display
    }

    // Filters subjects in table based on text input
    private void filterSubjects(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            subjectTable.setItems(masterSubjectList); // Reset if search is empty
            return;
        }

        String lowerCaseFilter = filterText.toLowerCase();

        ObservableList<Subject> filteredList = masterSubjectList.filtered(subject ->
                (subject.getSubjectName() != null && subject.getSubjectName().toLowerCase().contains(lowerCaseFilter)) ||
                        (subject.getSubjectCode() != null && subject.getSubjectCode().toLowerCase().contains(lowerCaseFilter))
        );

        subjectTable.setItems(filteredList);
    }
}
