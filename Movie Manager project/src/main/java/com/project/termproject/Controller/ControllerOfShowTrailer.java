package com.project.termproject.Controller;

import com.project.termproject.Data.Movies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerOfShowTrailer {
    @FXML
    WebView trailer;
    WebEngine engine;
    @FXML
    Button closeButton;
    String urlcode;


    public void close(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void init(String urlcode) {
        this.urlcode = urlcode;
    }
    public void loadPage(){
        System.out.println(urlcode + " in show trailer");
        engine = trailer.getEngine();
        System.out.println(urlcode + " in show trailer");
        String str = "https://www.youtube.com/embed/" + urlcode;
        engine.load(str);
    }

}
