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
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FacultyEventsController {

    private Faculty faculty;

    public void setUser(Faculty faculty) {
        this.faculty = faculty;

    }
    @FXML
    private TextField EventCapacityField;

    @FXML
    private TextField EventCostField;

    @FXML
    private TextField EventDescriptionField;

    @FXML
    private TextField EventHeaderField;

    @FXML
    private TextField EventLoationField;

    @FXML
    private TextField EventStudentField;

    @FXML
    private DatePicker EventdatePickerField;

    @FXML
    private TableColumn<?, ?> eventCapacityCol;

    @FXML
    private TableColumn<?, ?> eventCodeCol;

    @FXML
    private TextField eventCodeField;

    @FXML
    private TableColumn<?, ?> eventCostCol;

    @FXML
    private TableColumn<?, ?> eventDateCol;

    @FXML
    private TableColumn<?, ?> eventDesCol;

    @FXML
    private TableColumn<?, ?> eventHeaderCol;

    @FXML
    private TableColumn<?, ?> eventLocationCol;

    @FXML
    private TableColumn<?, ?> eventNameCol;

    @FXML
    private TextField eventNameField;

    @FXML
    private TableColumn<?, ?> eventStudentCol;

    @FXML
    private TableView<?> eventsTable;

    @FXML
    void addEvent(ActionEvent event) {

    }

    @FXML
    void viewEvents(ActionEvent event) {

    }

}
