package com.project.termproject.Controller;

import com.project.termproject.Client.WriteThreadClient;
import com.project.termproject.Data.LocalDatabase;
import com.project.termproject.Data.Movies;
import com.project.termproject.ProductionCompanyPageUpdater;
import com.project.termproject.ReadThread;
import com.project.termproject.dto.soldPackage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerOfConfirmationSell {
    @FXML
    Label movieName;
    @FXML
    Label errorLabel;
    @FXML
    TextField destField;
    @FXML
    Button sellButton;
    @FXML
    Button cancelButton;
    private String destination ;
    private Movies movie;
    ProductionCompanyPageUpdater productionCompanyPageUpdater;
    public void cancel(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void sell(ActionEvent event) {
        destination = destField.getText();
        if (destination.isEmpty()) return;
        System.out.println("yo in confirmation sell sell.");
        //WriteThreadClient.write(new soldPackage(movie,destination,true));
        ReadThread.write(new soldPackage(movie,destination,true));
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
    public void init(Movies movie){
        System.out.println(movie.getTitle());
        this.movie = movie;
        updateMovieInfoUI();

    }
    private void updateMovieInfoUI() {
        movieName.setText(movie.getTitle());
    }
}
