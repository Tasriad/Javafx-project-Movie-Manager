package com.project.termproject.Controller;

import com.project.termproject.Data.Movies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerOfMovieDetails {
    private Movies movie;
    @FXML
    Label movieName;
    @FXML
    Label productionCompanyName;
    @FXML
    Label releaseYear;
    @FXML
    Label genre1;
    @FXML
    Label genre2;
    @FXML
    Label genre3;
    @FXML
    Label runTime;
    @FXML
    Label revenue;
    @FXML
    Label budget;
    @FXML
    Button coverButton;
    @FXML
    Button closeButton;
    @FXML
    Button trailerButton;
    @FXML
    DialogPane dialogpane;

    public DialogPane getDialogpane() {
        return dialogpane;
    }

    public void close(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void showCover(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ShowCover.fxml"));
            DialogPane dialogPane = fxmlLoader.load();
            ControllerOfShowCover controllerOfShowCover = fxmlLoader.getController();
            System.out.println(movie.getTrailerLink() + "yo");
            controllerOfShowCover.init(movie.getId());
            Dialog dialog = new Dialog();
            dialog.setDialogPane(dialogPane);
            dialog.initStyle(StageStyle.UNDECORATED);
            controllerOfShowCover.show();
            dialog.showAndWait();
        } catch (IOException e) {
            System.out.println("you are getting this error");
            e.printStackTrace();
        }
    }
    public void init(Movies movie){
        this.movie = movie;
        updateMovieUi();
    }

    private void updateMovieUi() {
        movieName.setText(movie.getTitle());
        productionCompanyName.setText(movie.getProduction_comapny());
        releaseYear.setText(Integer.toString(movie.getRelease_year()));
        runTime.setText(Integer.toString(movie.getRunning_time()));
        genre1.setText(movie.getGenre()[0]);
        genre2.setText(movie.getGenre()[1]);
        genre3.setText(movie.getGenre()[2]);
        budget.setText(Integer.toString(movie.getBudget()));
        revenue.setText(Integer.toString(movie.getRevenue()));
    }

    public void showTrailer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ShowTrailer.fxml"));
            DialogPane dialogPane = fxmlLoader.load();
            ControllerOfShowTrailer controllerOfShowTrailer = fxmlLoader.getController();
            System.out.println(movie.getTrailerLink() + "yo");
            controllerOfShowTrailer.init(movie.getTrailerLink());
            Dialog dialog = new Dialog();
            dialog.setDialogPane(dialogPane);
            dialog.initStyle(StageStyle.UNDECORATED);
            controllerOfShowTrailer.loadPage();
            dialog.showAndWait();
        } catch (IOException e) {
            System.out.println("you are getting this error");
            e.printStackTrace();
        }
    }
}
