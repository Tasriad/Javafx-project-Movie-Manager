module com.project.termproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.project.termproject to javafx.fxml;
    exports com.project.termproject;
    exports com.project.termproject.Controller;
    opens com.project.termproject.Controller to javafx.fxml;
    exports com.project.termproject.Server;
    opens com.project.termproject.Server to javafx.fxml;
}