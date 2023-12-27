package com.project.termproject.Controller;

import com.project.termproject.Data.Movies;
import com.project.termproject.Data.ProductionCompany;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerOfMovieMarket {
    @FXML
    Label movieName;
    @FXML
    Label productionCompanyName;
    @FXML
    Button buyButton;
    @FXML
    Button detailsButton;
    private Movies movie;
    private ProductionCompany productionCompany;
    public void buy(ActionEvent event) {
    }

    public void details(ActionEvent event) {
    }
    public void inti(Movies movie,ProductionCompany productionCompany){
        this.movie = movie;
        this.productionCompany = productionCompany;
        if (movie.getProductionCompany().getId() == productionCompany.getId()) buyButton.setDisable(true);
        updateMovieInfoUI();
    }

    private void updateMovieInfoUI() {
        movieName.setText(movie.getTitle());
        productionCompanyName.setText(movie.getProduction_comapny());
    }
}
