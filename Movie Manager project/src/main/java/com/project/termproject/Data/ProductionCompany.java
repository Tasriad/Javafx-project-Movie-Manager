package com.project.termproject.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductionCompany implements Serializable {
    int id;
    String name;
    String password;
    List<Movies> movies = new ArrayList<>();
    int movieCount;

    public ProductionCompany(String name,String password){
        this.name = name;
        this.password = password;
    }
    public ProductionCompany(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    public List<Movies> getMovies() {
        return movies;
    }
    public void addMovie(Movies movie){
        movies.add(movie);
        movie.setProductionCompany(this);
    }
    public void removeMovie(Movies movie){
        movies.remove(movie);
    }
}
