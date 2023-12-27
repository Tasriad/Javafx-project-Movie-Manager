package com.project.termproject.Data;

import com.project.termproject.NetworkUtil.SocketWrapper;
import com.project.termproject.Server.Server;
import com.project.termproject.dto.UpdateRespond;
import com.project.termproject.dto.soldPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CentralDatbase {

    public static CentralDatbase instance;
    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";
    private static final String INPUT_PASSWORD_FILE_NAME = "password.txt";
    private static final String OUTPUT_PASSWORD_FILE_NAME = "password.txt";

    List<Movies> movies = new ArrayList();
    List<ProductionCompany> productionCompanies = new ArrayList<>();
    HashMap<String, ProductionCompany> productionCompanyMap = new HashMap<>();

    private CentralDatbase() {
        try {
            System.out.println("Reading files................");
            readFromInputFile();
            readPasswordsFromFile();
            System.out.println("Reading files done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CentralDatbase getInstance() {
        if (instance == null) {
            instance = new CentralDatbase();
        }
        return instance;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Movies> getMovies() {
        return movies;
    }
    public synchronized  UpdateRespond sellProcess(soldPackage sp){
        for(int i=0;i<movies.size();i++){
            if(movies.get(i).getTitle().equals(sp.getMovie().getTitle())){
                Movies m = sp.getMovie();
                m.setProduction_comapny(sp.getDestination());
                movies.remove(movies.get(i));
                movies.add(m);
            }
        }
        UpdateRespond respond = new UpdateRespond(sp.getMovie(),sp.getDestination(),movies);
        return respond;
    }

    public void addMovie(Movies movie){
        movie.setMovieNo(movies.size());
        movies.add(movie);
    }
    public ProductionCompany addProductionCompany(String productionCompanyName){
        if (productionCompanyMap.containsKey(productionCompanyName)) {
            return productionCompanyMap.get(productionCompanyName);
        }
        for(ProductionCompany pc : productionCompanies){
            if(pc.getName().equals(productionCompanyName)){
                return pc;
            }
        }
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName(productionCompanyName);
        productionCompanyMap.put(productionCompanyName, productionCompany);
        productionCompany.setId(productionCompanies.size());
        productionCompanies.add(productionCompany);
        return productionCompany;
    }

    public void readPasswordsFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_PASSWORD_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
             ProductionCompany productionCompany = addProductionCompany(tokens[0]);
            productionCompany.setPassword(tokens[1]);
        }
        br.close();
    }
    public void writePasswordToInputFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PASSWORD_FILE_NAME));
        for (ProductionCompany productionCompany : productionCompanies) bw.write(productionCompany.getName() + "," + productionCompany.getPassword() + "\n");
        bw.close();
    }
    public void readFromInputFile() throws IOException {
        BufferedReader movie_file = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String movie_string = movie_file.readLine();
            if (movie_string == null) {
                break;
            }
            String[] movie_info = movie_string.split(",");
            ProductionCompany productionCompany = new ProductionCompany();
            if(!productionCompanies.contains(productionCompany)){
                addProductionCompany(movie_info[6]);
            }
            String[] genre = new String[3];
            System.arraycopy(movie_info, 2, genre, 0, 3);
            Movies movie = new Movies(movie_info[0], Integer.parseInt(movie_info[1]), genre, Integer.parseInt(movie_info[5]), movie_info[6], Integer.parseInt(movie_info[7]), Integer.parseInt(movie_info[8]));
            if(movie_info.length >9){
                movie.setTrailerLink(movie_info[9]);
            }
            if(movie_info.length >10){
                movie.setId(movie_info[10]);
            }
            if(!movie.isBeingTransferred){
                movies.add(movie);
            }
        }
        movie_file.close();
    }
    public void writeToInputFile() throws IOException {
        BufferedWriter movie_file = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(ProductionCompany productionCompany : productionCompanies){
            for (Movies m : productionCompany.getMovies()){
                movie_file.write(m.getTitle() + "," + m.getRelease_year() + "," + m.getGenre()[0] + "," + m.getGenre()[1] + "," + m.getGenre()[2] + "," + m.getRunning_time() + "," + m.getProduction_comapny() + "," + m.getBudget() + "," + m.getRevenue());
                movie_file.write(System.lineSeparator());
            }
        }
        movie_file.close();
    }

    public ProductionCompany checkProductionCompany(String productionCompanyName){
        return productionCompanyMap.get(productionCompanyName);
    }
    public ProductionCompany signUpProductionCompany(String productionCompnyName, String password) {
        for (var productionCompany : productionCompanies) {
            if (productionCompany.getName().equals(productionCompnyName)) return null;
        }
        ProductionCompany productionCompany = new ProductionCompany(productionCompnyName,password);
        productionCompanyMap.put(productionCompnyName, productionCompany);
        productionCompany.setId(productionCompanies.size());
        productionCompanies.add(productionCompany);
        return productionCompany;
    }


}
