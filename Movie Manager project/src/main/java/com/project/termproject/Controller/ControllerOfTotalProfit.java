package com.project.termproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerOfTotalProfit {
    @FXML
    Label PCname;
    @FXML
    Label profit;
    @FXML
    Button closeButton;
    public void close(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
    public void init(String productionCompanyName,long totalProfit){
        PCname.setText(productionCompanyName);
        profit.setText(totalProfit + " $");
    }
}
