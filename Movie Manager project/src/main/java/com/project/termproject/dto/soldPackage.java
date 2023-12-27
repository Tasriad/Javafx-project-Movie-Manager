package com.project.termproject.dto;

import com.project.termproject.Data.Movies;

import java.io.Serializable;

public class soldPackage implements Serializable {
   private Movies movie;
   private String destination;
   public boolean refreshRequest;
   public boolean sellValidity;

   public soldPackage(Movies movie,String destination){
      this.movie = movie;
      this.destination = destination;
   }
   public soldPackage(Movies movie,String destination,boolean refreshRequest){
      this.movie = movie;
      this.destination = destination;
      this.refreshRequest = refreshRequest;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public String getDestination() {
      return destination;
   }

   public void setMovie(Movies movie) {
      this.movie = movie;
   }

   public Movies getMovie() {
      return movie;
   }

}
