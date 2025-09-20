// Developed by PK
package com.example.scenebuild;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import university.Event;
import university.EventManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class StudentEventsPageController {

    // Fields (fx:id matches your FXML)
    @FXML private TextField EventStudentField;
    @FXML private Button updateStudentButton;
    @FXML private TableColumn<Event, String> eventStudentCol;

    @FXML private TextField eventNameField;
    @FXML private TextField eventCodeField;
    @FXML private TextField EventDescriptionField;
    @FXML private TextField EventLoationField;
    @FXML private TextField EventCostField;
    @FXML private TextField EventHeaderField;
    @FXML private TextField EventCapacityField;
    @FXML private DatePicker EventdatePickerField;

    @FXML private TableView<Event> eventsTable;
    @FXML private TableColumn<Event, String> eventNameCol;
    @FXML private TableColumn<Event, String> eventCodeCol;
    @FXML private TableColumn<Event, String> eventDesCol;
    @FXML private TableColumn<Event, String> eventLocationCol;
    @FXML private TableColumn<Event, String> eventCostCol;
    @FXML private TableColumn<Event, String> eventHeaderCol;
    @FXML private TableColumn<Event, String> eventDateCol;
    @FXML private TableColumn<Event, Integer> eventCapacityCol;

    private final EventManager eventManager = new EventManager();
    private final ObservableList<Event> eventObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadEvents(); // Preload data

        // Allow row click to populate Registered Student field
        eventsTable.setOnMouseClicked(event -> {
            Event selected = eventsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                EventStudentField.setText(String.join(",", selected.getRegisteredStudents()));
            }
        });
    }

    private void setupTable() {
        eventStudentCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.join(",", data.getValue().getRegisteredStudents())));

        eventNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventName()));
        eventCodeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventCode()));
        eventDesCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        eventLocationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));
        eventCostCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCost()));
        eventHeaderCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHeaderImage()));
        eventDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateTime()));
        eventCapacityCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCapacity()).asObject());

        eventsTable.setItems(eventObservableList);
    }

    @FXML
    private void addEvent() {
        try {
            String name = eventNameField.getText();
            String code = eventCodeField.getText();
            String desc = EventDescriptionField.getText();
            String location = EventLoationField.getText();
            String cost = EventCostField.getText();
            String headerImage = EventHeaderField.getText();
            int capacity = Integer.parseInt(EventCapacityField.getText());
            String date = EventdatePickerField.getValue() != null ? EventdatePickerField.getValue().toString() : "";

            Event event = new Event(code, name, desc, location, date, capacity, cost, headerImage);
            eventManager.addEvent(event);
            showAlert("Success", "Event added successfully!", Alert.AlertType.INFORMATION);

            loadEvents(); // Refresh table
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Please enter valid values.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void viewEvents() {
        loadEvents();
    }

    @FXML
    private void handleUpdateStudent() {
        Event selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
        String updatedStudents = EventStudentField.getText();

        if (selectedEvent == null || updatedStudents.isBlank()) {
            showAlert("Warning", "Select an event and enter student name(s).", Alert.AlertType.WARNING);
            return;
        }

        String targetCode = selectedEvent.getEventCode();

        try {
            Path path = Paths.get("TextData/Events.txt");
            List<String> lines = Files.readAllLines(path);
            List<String> updatedLines = new ArrayList<>();

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (i == 0) {
                    updatedLines.add(line); // Keep header
                    continue;
                }

                String[] parts = line.split("\t");
                //if (parts.length == 9 && parts[0].equals(targetCode)) {
                  //  parts[8] = updatedStudents;
                  //  line = String.join("\t", parts);
               // }
                if (parts[0].equals(targetCode)) {
                    // Ensure parts has 9 elements
                    if (parts.length < 9) {
                        // pad with empty string
                        while (parts.length < 9) {
                            parts = java.util.Arrays.copyOf(parts, parts.length + 1);
                            parts[parts.length - 1] = "";
                        }
                    }
                    parts[8] = updatedStudents;
                    line = String.join("\t", parts);
                }

                updatedLines.add(line);
            }

            Files.write(path, updatedLines);
            showAlert("Success", "Registered Student(s) updated successfully!", Alert.AlertType.INFORMATION);

            loadEvents(); // Refresh table

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error updating student.", Alert.AlertType.ERROR);
        }
    }

    private void loadEvents() {
        eventObservableList.clear();
        eventObservableList.addAll(eventManager.getEvents());
        eventsTable.refresh();
    }

    private void clearForm() {
        eventNameField.clear();
        eventCodeField.clear();
        EventDescriptionField.clear();
        EventLoationField.clear();
        EventCostField.clear();
        EventHeaderField.clear();
        EventCapacityField.clear();
        EventdatePickerField.setValue(null);
        EventStudentField.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
