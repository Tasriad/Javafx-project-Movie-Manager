package com.project.termproject.Server;

import com.project.termproject.Data.Movies;
import com.project.termproject.NetworkUtil.SocketWrapper;
import com.project.termproject.dto.UpdateRespond;

public class ClientInfo {
    private  SocketWrapper socketWrapper;
    private String productionCompanyName ;

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    public String getProductionCompanyName() {
        return productionCompanyName;
    }

    public ClientInfo(SocketWrapper socketWrapper,String productionCompanyName){
        this.socketWrapper = socketWrapper;
        this.productionCompanyName= productionCompanyName;
    }
    public void write(Object obj){
        try {
            if(obj instanceof UpdateRespond){
                UpdateRespond updateRespond = (UpdateRespond) obj;
                for(Movies m : updateRespond.getMovies()){
                    System.out.println("in client info: " + m.getTitle() + " " + m.getProduction_comapny());
                }
                updateRespond.refreshPCP = false;
                socketWrapper.write(updateRespond);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
