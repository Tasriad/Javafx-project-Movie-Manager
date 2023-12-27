package com.project.termproject.Data;

import java.io.Serializable;
import java.util.Arrays;

public class Movies implements Serializable {
    int movieNo;
    String id;
    private String Title;
    private int Release_year;
    private String[] Genre;
    private int Running_time;
    private String Production_comapny;
    private  ProductionCompany productionCompany;
    private int Budget;
    private int Revenue;
    private String trailerLink;
    public boolean isBeingTransferred = false;

    public Movies() {
        Title = "";
        Production_comapny = "";
        Release_year = Revenue = Budget = Running_time = 0;
        Genre = new String[3];
        Arrays.fill(Genre, "");
    }

    public Movies(String Title, int Release_year, String[] Genre, int Running_time, String Production_company, int Budget, int Revenue) {
        this.Title = Title;
        this.Release_year = Release_year;
        this.Genre = new String[3];
        this.Genre = Genre.clone();
        this.Running_time = Running_time;
        this.Production_comapny = Production_company;
        this.Budget = Budget;
        this.Revenue = Revenue;
    }
    public Movies(Movies movie){
        this.Title = movie.Title;
        this.Genre = new String[3];
        this.Genre = movie.Genre.clone();
        this.Budget = movie.Budget;
        this.productionCompany = movie.productionCompany;
        this.Revenue = movie.Revenue;
        this.Running_time = movie.Running_time;
        this.Release_year = movie.Release_year;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMovieNo(int movieNo) {
        this.movieNo = movieNo;
    }

    public void setRelease_year(int release_year) {
        Release_year = release_year;
    }

    public void setGenre(String[] genre) {
        Genre = genre;
    }

    public void setRunning_time(int running_time) {
        Running_time = running_time;
    }

    public void setProduction_comapny(String production_comapny) {
        Production_comapny = production_comapny;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public void setRevenue(int revenue) {
        Revenue = revenue;
    }

    public void setBeingTransferred(boolean beingTransferred) {
        isBeingTransferred = beingTransferred;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getMovieNo() {
        return movieNo;
    }

    public String getTitle() {
        return Title;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public int getRelease_year() {
        return Release_year;
    }

    public String[] getGenre() {
        return Genre;
    }

    public int getRunning_time() {
        return Running_time;
    }

    public String getProduction_comapny() {
        return Production_comapny;
    }

    public int getBudget() {
        return Budget;
    }

    public int getRevenue() {
        return Revenue;
    }

    public int getProfit() {
        return (Revenue - Budget);
    }

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }

    public void displayMovieInfo() {
        System.out.println("Movie Title: " + getTitle());
        System.out.println("Release Year: " + getRelease_year());
        System.out.print("Genre: " + getGenre()[0]);
        if (!(getGenre()[1].isEmpty())) {
            System.out.print("," + getGenre()[1]);
        }
        if (!(getGenre()[2].isEmpty())) {
            System.out.print("," + getGenre()[2]);
        }
        System.out.println();
        System.out.println("Running Time: " + getRunning_time() + " " + "minutes");
        System.out.println("Production Company: " + getProduction_comapny());
        System.out.println("Budget: " + getBudget() + " " + "USD");
        System.out.println("Revenue: " + getRevenue() + " " + "USD");
        System.out.println();
    }
}
