package com.project.termproject.Controller;

import com.project.termproject.Data.Movies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerOfMovieBox {
    @FXML
    Label movieName;
    @FXML
    Label productionCompanyName;
    @FXML
    Button sellButton;
    @FXML
    Button detailsButton;
    private Movies movie;

    public Movies getMovie() {
        return movie;
    }

    public void sell(ActionEvent event) {
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("confirmSell.fxml"));
        try {
            DialogPane dialogPane = fxmlLoader.load();
            ControllerOfConfirmationSell controller = fxmlLoader.getController();
            controller.init(movie);
            Dialog dialog = new Dialog();
            dialog.setDialogPane(dialogPane);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void details(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MovieDetails.fxml"));
            DialogPane dialogPane = fxmlLoader.load();
            ControllerOfMovieDetails controllerOfMovieDetails = fxmlLoader.getController();
            System.out.println(movie.getTrailerLink());
            controllerOfMovieDetails.init(movie);
            Dialog dialog = new Dialog();
            dialog.setDialogPane(dialogPane);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.showAndWait();
        } catch (IOException e) {
            System.out.println("you are getting this error");
            e.printStackTrace();
        }
    }
    public void inti(Movies movie) {
        this.movie = movie;
        updateMovieInfoUI();
    }

    private void updateMovieInfoUI() {
        movieName.setText(movie.getTitle());
        productionCompanyName.setText(movie.getProduction_comapny());
    }

}
