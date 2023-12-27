package com.project.termproject.Server;

import com.project.termproject.Data.CentralDatbase;
import com.project.termproject.Data.Movies;
import com.project.termproject.Data.ProductionCompany;
import com.project.termproject.LoginUtil.SignupDTO;
import com.project.termproject.NetworkUtil.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private  HashMap<String,String> userMap;
    private List<Movies> moviesList;
    static int i=0;

    Server(CentralDatbase cd){
        userMap = new HashMap<>();
        moviesList = new ArrayList<>();
        for (ProductionCompany productionCompany : cd.getProductionCompanies()){
            addClient(productionCompany.getName(),productionCompany.getPassword());
        }
        addMovies(cd.getMovies());


        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server is waiting......");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void setUserMap(HashMap<String, String> userMap) {
        this.userMap = userMap;
    }

    public HashMap<String, String> getUserMap() {
        return userMap;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ReadThreadServer(this,userMap, socketWrapper);
    }

   synchronized public boolean addClient(String username,String password){
        for (String userName : userMap.keySet() ){
            if (userName.equalsIgnoreCase(username)){
                System.out.println("alredy in");
                return false;
            }
        }
        userMap.put(username,password);
        i++;
        System.out.println("here just added  : " + i + " " +username+" "+ userMap.get(username));
        return true;
    }
    synchronized public void addMovies(List<Movies> moviesList){
        this.setMoviesList(moviesList);
    }

    public static void main(String[] args) {
       CentralDatbase cd =  CentralDatbase.getInstance();
        new Server(cd);
    }
}
