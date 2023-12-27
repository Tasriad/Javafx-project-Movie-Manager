package com.project.termproject;

import com.project.termproject.Controller.*;
import com.project.termproject.Data.LocalDatabase;
import com.project.termproject.Data.Movies;
import com.project.termproject.Data.ProductionCompany;
import com.project.termproject.dto.UpdateRespond;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class ProductionCompanyPageUpdater {
    ControllerOfProductionCompanyPage controllerOfProductionCompanyPage;
    ControllerOfConfirmationSell controllerOfConfirmationSell;
    ProductionCompany productionCompany;
    Movies movie;
    int listNo = 1;
    int filterOption = 0;
    String searchString;
    LocalDatabase localDatabase;
   Dialog dialog = new Dialog();
   ControllerOfTotalProfit controllerOfTotalProfit = null;


    public ProductionCompanyPageUpdater(ControllerOfProductionCompanyPage controllerOfProductionCompanyPage) {
        this.controllerOfProductionCompanyPage = controllerOfProductionCompanyPage;
        this.localDatabase = LocalDatabase.getInstance();
    }
    public ProductionCompanyPageUpdater(ControllerOfConfirmationSell controllerOfConfirmationSell,ControllerOfProductionCompanyPage controllerOfProductionCompanyPage) {
        this.controllerOfConfirmationSell = controllerOfConfirmationSell;
        this.controllerOfProductionCompanyPage = controllerOfProductionCompanyPage;
        this.localDatabase = LocalDatabase.getInstance();
    }
    public void setListNo(int listNo) {
        this.listNo = listNo;
    }

    synchronized void filter(){
        if (filterOption == 0){
            /*for (Movies m: localDatabase.getMoviesOfPC()){
                System.out.println(m.getTitle() + " " + m.getProduction_comapny());
            }*/
            upDateGUI(localDatabase.getMoviesOfPC());
        }
        else if (filterOption == 1) {
            if (searchString.strip().isEmpty()) {
                return;
            }
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.searchByMovieTitle(searchString));
        }
        else if(filterOption == 2){
            if (searchString.strip().isEmpty()) {
                return;
            }
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.searchByReleaseYear(Integer.parseInt(searchString)));
        }
        else if (filterOption == 3){
            if (searchString.strip().isEmpty()) {
                return;
            }
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.searchByGenre(searchString));
        }
        else if(filterOption == 4){
            String[] str = searchString.split(",");
            if (str[0].strip().isEmpty() || str[1].strip().isEmpty()){
                return;
            }
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.searchByRuntimeRange(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }
        else if(filterOption == 5){
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.mostRecentMovies());
        }
        else if(filterOption == 6){
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.moviesWithMaxRevenue());
        }
        else if(filterOption == 7){
            System.out.println("yo in filter option 7" + " " + movie.getTitle());
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.remainderMovies(movie));
        }
        else if(filterOption == 8){
            localDatabase.setListToShow(listNo);
            upDateGUI(localDatabase.getMarketmovies());
        }
        else if(filterOption == 9){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    long totalProfit = localDatabase.totalProfit();
                    if(dialog.isShowing()) {
                        controllerOfTotalProfit.init(localDatabase.productionCompanyName, totalProfit);
                        return;
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("scenes/TotalProfit.fxml"));
                    try {
                        DialogPane dialogPane = fxmlLoader.load();
                        controllerOfTotalProfit = fxmlLoader.getController();
                        controllerOfTotalProfit.init(localDatabase.productionCompanyName, totalProfit);
                        dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public synchronized  void upDateGUI(List<Movies> movies){
        System.out.println("yo in upDate gui large.");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GridPane grid = controllerOfProductionCompanyPage.getGrid();
                 Label notFoundLabel = controllerOfProductionCompanyPage.getNotFoundLabel();
                System.out.println("before get children");
                 grid.getChildren().clear();
                System.out.println("After get children");
                if (movies.isEmpty()){
                    notFoundLabel.setVisible(true);
                    return;
                }
                notFoundLabel.setVisible(false);
                String address = null;
                if (listNo == 1) address = "scenes/MovieBox.fxml";
                else if (listNo == 2) address = "scenes/movieMarket.fxml";
                for (int i = 0; i < movies.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(address));
                    try {
                        AnchorPane anchorPane = fxmlLoader.load();
                        grid.add(anchorPane, 0, i + 1);
                        GridPane.setMargin(anchorPane, new Insets(10));
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("could not find file");
                    }

                    if (listNo == 1) {
                         ControllerOfMovieBox movieBox = fxmlLoader.getController();
                         movieBox.inti(movies.get(i));
                        System.out.println(movies.get(i).getTitle());
                    }
                    else {
                        ControllerOfMovieMarket movieMarket = fxmlLoader.getController();
                        movieMarket.inti(movies.get(i), productionCompany);
                    }
                }
            }
        });
    }
    public void updateGUI(int list,int filterOption) {
        setListNo(list);
       this.filterOption = filterOption;
        new Thread(this::filter).start();
    }
    public void updateGUI(int list,int filterOption,Movies movie) {
        setListNo(list);
        this.filterOption = filterOption;
        this.movie = movie;
        System.out.println("yo in updateGUI");
        new Thread(this::filter).start();
    }
    public void updateGUI(int searchOption, String searchString) {
        this.filterOption = searchOption;
        this.searchString = searchString;
        new Thread(this::filter).start();
    }
    public void refreshGUI(UpdateRespond updateRespond){
        listNo = 1;
        filterOption = 0;
        System.out.println("here in refresh");
        localDatabase.updateMovielist(updateRespond.movie,updateRespond.dest);
        localDatabase.setMovielistOfProductionCompanies();
        new Thread(this::filter).start();
    }
}
