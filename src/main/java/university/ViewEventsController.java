// Created by PK
package university;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewEventsController {

    @FXML
    private ListView<String> eventListView;

    private final String FILE_PATH = "TextData/Events.txt";

    @FXML
    public void initialize() {
        loadEventsFromFile();
    }

    private void loadEventsFromFile() {
        eventListView.getItems().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                if (parts.length >= 5) {
                    String summary = String.format("%s - %s (%s)", parts[0], parts[1], parts[4]);
                    eventListView.getItems().add(summary);
                } else {
                    eventListView.getItems().add("Invalid event entry: " + line);
                }
            }
        } catch (IOException e) {
            eventListView.getItems().add("Error loading events.");
            e.printStackTrace();
        }
    }
}
