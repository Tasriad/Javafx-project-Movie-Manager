package com.project.termproject.Data;

import com.project.termproject.Controller.ControllerOfConfirmationSell;
import com.project.termproject.Controller.ControllerOfProductionCompanyPage;
import com.project.termproject.LoginUtil.LoginDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ReleaseYearComparison implements Comparator<Movies> {
    public int compare(Movies m1, Movies m2) {
        if (m1.getRelease_year() == m2.getRelease_year()) {
            return 0;
        } else if (m1.getRelease_year() < m2.getRelease_year()) {
            return 1;
        } else
            return -1;
    }
}
class RevenueComparison implements Comparator<Movies> {
    public int compare(Movies m1, Movies m2) {
        if (m1.getRevenue() == m2.getRevenue()) {
            return 0;
        } else if (m1.getRevenue() < m2.getRevenue()) {
            return 1;
        } else
            return -1;
    }
}
public class LocalDatabase {
    public String productionCompanyName;
    List<Movies> moviesOfPC = new ArrayList<>();
    List<Movies> movies = new ArrayList<>();
    List<Movies> marketmovies = new ArrayList<>();
    List<Movies> filterMovies  = new ArrayList<>();
    ControllerOfProductionCompanyPage controllerOfProductionCompanyPage;
    ControllerOfConfirmationSell controllerOfConfirmationSell;

    private static LocalDatabase instance;

    public List<Movies> getMoviesOfPC() {
        return moviesOfPC;
    }

    public void setMoviesOfPC(List<Movies> moviesOfPC) {
        this.moviesOfPC = moviesOfPC;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
    public void updateMovielist(Movies movie,String dest){
        for(int i=0;i<movies.size();i++){
            if(movies.get(i).getTitle().equals(movie.getTitle())){
                Movies m = movie;
                m.setProduction_comapny(dest);
                movies.remove(movies.get(i));
                movies.add(m);
            }
        }
    }

    public void setListToShow(int i){
        if (i==1) filterMovies = moviesOfPC;
        else filterMovies = marketmovies;
    }

    public ControllerOfProductionCompanyPage getControllerOfProductionCompanyPage() {
        return controllerOfProductionCompanyPage;
    }

    public List<Movies> remainderMovies(Movies movie){
        System.out.println("yo in remainder movies" + " " + movie.getTitle());
        List<Movies> remmovies;
        if (moviesOfPC.isEmpty()){
            System.out.println("yup the problem you are thinking");
        }else{
            System.out.println("ok thats not the problem." + " " + moviesOfPC.get(0).getTitle() );
        }
        remmovies = moviesOfPC;
        System.out.println("in remmovies before remove" + " " + remmovies.get(0).getTitle());
        System.out.println("yo before remove");
        remmovies.remove(movie);
        marketmovies.add(movie);
        return remmovies;
    }
    public List<Movies> searchByMovieTitle(String searchTitle) {
        String[] movieNames = searchTitle.split(",");
       List<Movies> movies = new ArrayList<>();
        for (String s : movieNames){
            for (Movies m : filterMovies) {
                if (m.getTitle().equalsIgnoreCase(searchTitle)) {
                    movies.add(m);
                    System.out.println(m.getTitle());
                }
            }
        }
        return movies;
    }
    public List<Movies> searchByReleaseYear(int searchYear) {
        List<Movies> moviesOfSameReleaseYear = new ArrayList<>();
        for (Movies m : filterMovies) {
            if (m.getRelease_year() == searchYear) {
                moviesOfSameReleaseYear.add(m);
            }
        }
        if (moviesOfSameReleaseYear.isEmpty()) {
            return moviesOfSameReleaseYear;
        }
        return moviesOfSameReleaseYear;
    }
    public List<Movies> searchByGenre(String searchGenre) {
        List<Movies> moviesOfSameGenre = new ArrayList<>();
        for (Movies m : filterMovies) {
            if (searchGenre.equalsIgnoreCase(m.getGenre()[0]) || searchGenre.equalsIgnoreCase(m.getGenre()[1]) || searchGenre.equalsIgnoreCase(m.getGenre()[2])) {
                moviesOfSameGenre.add(m);
            }
        }
        if (moviesOfSameGenre.isEmpty()) {

            return moviesOfSameGenre;
        }
        return moviesOfSameGenre;
    }
    public List<Movies> searchByRuntimeRange(int lowerRange,int upperRange) {
        List<Movies> moviesInRuntimeRange = new ArrayList<>();
        for (Movies m : filterMovies) {
            if (m.getRunning_time() >= lowerRange && m.getRunning_time() <= upperRange) {
                moviesInRuntimeRange.add(m);
            }
        }
        if (moviesInRuntimeRange.isEmpty()) {
            return moviesInRuntimeRange;
        }
        return moviesInRuntimeRange;
    }
    public List<Movies> mostRecentMovies() {
        List<Movies> clone = new ArrayList<>();
        clone = filterMovies;
        List<Movies> moviesOfRecentYear = new ArrayList<>();
        if (clone.isEmpty()) {
            System.out.println("NO SUCH MOVIE WITH THIS PRODUCTION COMPANY.");
            return null;
        }
        Collections.sort(clone, new ReleaseYearComparison());
        int recentYear = clone.get(0).getRelease_year();
        for (Movies m : clone) {
            if (m.getRelease_year() == recentYear) {
                moviesOfRecentYear.add(m);
            }
        }
        return moviesOfRecentYear;
    }
    public List<Movies> moviesWithMaxRevenue() {
        List<Movies> clone = new ArrayList<>();
        clone = filterMovies;
        List<Movies> movies_with_max_revenue = new ArrayList<>();
        Collections.sort(clone, new RevenueComparison());
        int max_revenue = clone.get(0).getRevenue();
        for (Movies m : clone) {
            if (m.getRevenue() == max_revenue) {
                movies_with_max_revenue.add(m);
            }
        }
        return movies_with_max_revenue;
    }
    public long totalProfit() {
        long total_profit = 0;
        for (Movies m : moviesOfPC) {
            total_profit += m.getProfit();
        }
        return total_profit;
    }
    public void setControllerOfProductionCompanyPage(ControllerOfProductionCompanyPage controllerOfProductionCompanyPage) {
        this.controllerOfProductionCompanyPage = controllerOfProductionCompanyPage;
    }

    public void setControllerOfConfirmationSell(ControllerOfConfirmationSell controllerOfConfirmationSell) {
        this.controllerOfConfirmationSell = controllerOfConfirmationSell;
    }

    private LocalDatabase() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LocalDatabase getInstance() {
        if (instance == null) {
            instance = new LocalDatabase();
        }
        return instance;
    }
    public static LocalDatabase getInstance(LoginDTO loginDTO){
        if (instance == null) {
            instance = new LocalDatabase();
        }
        instance.movies = loginDTO.getMoviesList();
        instance.productionCompanyName = loginDTO.getUserName();
        instance.setMovielistOfProductionCompanies();
        return instance;
    }

    public List<Movies> getMarketmovies() {
        return marketmovies;
    }

    public void removeMovieFromPC(Movies movie){
        for (Movies m: moviesOfPC){
            if(m.getTitle().equals(movie.getTitle())){
                moviesOfPC.remove(movie);
                break;
            }
        }
    }
    public void removeFromMarket(Movies movie){
        for (Movies m: marketmovies){
            if(m.getTitle().equals(movie.getTitle())){
                marketmovies.remove(movie);
                break;
            }
        }
    }
    public void setMovielistOfProductionCompanies(){
        moviesOfPC = new ArrayList<>();
        if(!movies.isEmpty()){
            for(Movies m : movies){
                if(productionCompanyName.equals(m.getProduction_comapny())){
                    moviesOfPC.add(m);
                }
            }
        }
    }
}
