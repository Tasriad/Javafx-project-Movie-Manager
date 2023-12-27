package com.project.termproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOfShowCover {
    @FXML
    ImageView cover;
    @FXML
    Button closeButton;
    String id ;
    public void close(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
    public void show(){
        String str = "images/"+id+".png";
        Image img = new Image(getClass().getResourceAsStream(str));
        cover.setImage(img);
    }

    public void init(String id) {
        this.id = id;
    }
}
