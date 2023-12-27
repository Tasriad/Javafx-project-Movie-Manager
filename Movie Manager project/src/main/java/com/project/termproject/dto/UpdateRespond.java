package com.project.termproject.dto;

import com.project.termproject.Data.Movies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UpdateRespond implements Serializable {
   public Movies movie ;
   public List<Movies> movies = new ArrayList<>();
    public boolean refreshPCP = true ;
    public String dest;

    public UpdateRespond(){

    }
    public UpdateRespond(Movies movie,String dest,List<Movies> movies){
        this.movie = movie;
        this.dest = dest;
        this.movies = movies;
    }
    public UpdateRespond(UpdateRespond ur){
        this.movie = ur.movie;
        this.dest = ur.dest;
    }
    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    public List<Movies> getMovies() {
        return movies;
    }

}
