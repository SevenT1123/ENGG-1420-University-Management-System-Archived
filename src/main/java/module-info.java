module com.example.scenebuild {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.logging;


    opens com.example.scenebuild to javafx.fxml;
    exports com.example.scenebuild;
    opens com.example.scenebuild.Student to javafx.fxml;
    opens com.example.scenebuild.Faculty to javafx.fxml;
    opens com.example.scenebuild.Admin to javafx.fxml;
    opens university to javafx.base;

}
