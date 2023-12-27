package com.project.termproject.LoginUtil;

import com.project.termproject.Data.Movies;
import com.project.termproject.Data.ProductionCompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDTO implements Serializable {
    private String userName;
    private String password;
    private boolean status;
    ProductionCompany productionCompany;
    List<Movies> moviesList = new ArrayList<>();
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void setProductionCompany(ProductionCompany productionCompany) {
        this.productionCompany = productionCompany;
    }

    public ProductionCompany getProductionCompany() {
        return productionCompany;
    }
}
