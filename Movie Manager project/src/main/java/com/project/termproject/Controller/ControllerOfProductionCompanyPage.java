package com.project.termproject.Controller;

import com.project.termproject.Data.LocalDatabase;
import com.project.termproject.MainApplication;
import com.project.termproject.ProductionCompanyPageUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerOfProductionCompanyPage {
    private MainApplication main;
    private int searchOption = 0;
    String searchString;
    ProductionCompanyPageUpdater productionCompanyPageUpdater;
   public static ControllerOfProductionCompanyPage instance;

    @FXML
    private Button logoutButton;
    @FXML
    private Button MRMbutton;
    @FXML
    private Button MMRbutton;
    @FXML
    private Button TPbutton;
    @FXML
    private Button BTbutton;
    @FXML
    private Button BRYbutton;
    @FXML
    private Button BGbutton;
    @FXML
    private Button BRTbutton;
    @FXML
    private Button homeButton;
    @FXML
    private Button marketButton;
    @FXML
    private Button AMButton;
    @FXML
    private Label PCnameLabel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label notFoundLabel;
    @FXML
    Button cancelSearch;
    @FXML
    Button searchButton;
    @FXML
    TextField searchField;
    @FXML
    TextField fromField;
    @FXML
    TextField toField;
    @FXML
    Label min1;
    @FXML
    Label min2;

    public static ControllerOfProductionCompanyPage getInstance(){
        if (instance == null){
            instance = new ControllerOfProductionCompanyPage();
        }
        return instance;
    }

    public ProductionCompanyPageUpdater getProductionCompanyPageUpdater() {
        return productionCompanyPageUpdater;
    }

    public void hide(){
        searchField.setVisible(false);
        cancelSearch.setVisible(false);
        min1.setVisible(false);
        min2.setVisible(false);
        fromField.setVisible(false);
        toField.setVisible(false);
        searchButton.setVisible(false);
    }
    public void showSearchField(int searchNO){
        searchField.setVisible(true);
        if (searchNO == 1){
            searchField.setPromptText("Enter Movie Title");
        }else if(searchNO == 2){
            searchField.setPromptText("Enter Release Year");
        }else if(searchNO == 3){
            searchField.setPromptText("Enter Genre");
        }
        searchButton.setVisible(true);
        cancelSearch.setVisible(true);
        min1.setVisible(false);
        min2.setVisible(false);
        fromField.setVisible(false);
        toField.setVisible(false);
    }
    public void fromToSearch(){
        fromField.setVisible(true);
        toField.setVisible(true);
        min1.setVisible(true);
        min2.setVisible(true);
        searchButton.setVisible(true);
        cancelSearch.setVisible(true);
        searchField.setVisible(false);
    }
    public void showHomeButton(){
        homeButton.setVisible(true);
        marketButton.setVisible(false);
    }
    public void search(){
        productionCompanyPageUpdater.updateGUI(searchOption,searchString);
    }
    public GridPane getGrid() {
        return grid;
    }

    public Label getNotFoundLabel() {
        return notFoundLabel;
    }

    public void setMain(MainApplication main) {
        this.main = main;
    }

    public void labelSetter(String PCname) {
        PCnameLabel.setText(PCname);
    }

    public void logOut(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostRecentMovies(ActionEvent event) {
        productionCompanyPageUpdater.updateGUI(5,null);
    }

    public void moviesWithMaxRevenue(ActionEvent event) {
        productionCompanyPageUpdater.updateGUI(6,null);
    }

    public void totalProfit(ActionEvent event) {
        searchOption = 9;
        search();
    }

    public void addMovie(ActionEvent event) {
    }
    public void showProductionCompanyAssets(){
        productionCompanyPageUpdater.updateGUI(1,0);
    }
    public void init(MainApplication main){
        this.main = main;
        productionCompanyPageUpdater = new ProductionCompanyPageUpdater(this);
        LocalDatabase.getInstance().setControllerOfProductionCompanyPage(this);
        showProductionCompanyAssets();
    }

    public void searchPressed(ActionEvent event) {
        if (searchOption==4) searchString = fromField.getText()+","+toField.getText();
        else searchString = searchField.getText();
        search();
    }

    public void cancelSearchPressed(ActionEvent event) {
        searchField.setText(null);
        fromField.setText(null);
        toField.setText(null);
        hide();
        productionCompanyPageUpdater.updateGUI(0,null);
    }

    public void searchByTitle(ActionEvent event) {
        showSearchField(1);
        searchOption = 1;
    }

    public void searchByReleaseYear(ActionEvent event) {
        showSearchField(2);
        searchOption = 2;
    }

    public void searchByGenre(ActionEvent event) {
        showSearchField(3);
        searchOption = 3;
    }

    public void searchByRunningTime(ActionEvent event) {
        fromToSearch();
        searchOption = 4;
    }

    public void home(ActionEvent event) {
    }

    public void market(ActionEvent event) {
        showHomeButton();
        PCnameLabel.setText("MARKET");
        productionCompanyPageUpdater.updateGUI(2,8);
    }
}
