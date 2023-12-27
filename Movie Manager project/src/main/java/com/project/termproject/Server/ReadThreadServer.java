package com.project.termproject.Server;

import com.project.termproject.Data.CentralDatbase;
import com.project.termproject.Data.Movies;
import com.project.termproject.Data.ProductionCompany;
import com.project.termproject.LoginUtil.LoginDTO;
import com.project.termproject.LoginUtil.SignupDTO;
import com.project.termproject.NetworkUtil.SocketWrapper;
import com.project.termproject.dto.UpdateRespond;
import com.project.termproject.dto.soldPackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable{
    private Server server;
    private CentralDatbase cd;
    private final Thread thread;
    private  SocketWrapper socketWrapper;
    public HashMap<String,String> userMap;

    private static List<ClientInfo> clientList = new ArrayList<>();

   public ReadThreadServer(Server server,HashMap<String,String> map,SocketWrapper socketWrapper){
        this.server = server;
       this.userMap = map;
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
    }
    public synchronized void sell(soldPackage sp) {
       UpdateRespond updateRespond = CentralDatbase.getInstance().sellProcess(sp);
        for (Movies m : updateRespond.getMovies()){
            System.out.println("in update responnd " + m.getTitle() + " " + m.getProduction_comapny() + " " + updateRespond.dest + "will change movie: " + updateRespond.movie.getTitle());
        }
        for (var client : clientList) {
            System.out.println(client.getProductionCompanyName());
            client.write(updateRespond);
        }
    }
    @Override
    public void run() {
        try {
            while (true){
                Object o = socketWrapper.read();
                System.out.println(o);
                System.out.println("yo in run of read thread server");
                if(o != null){
                    if (o instanceof LoginDTO){
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        System.out.println("this is read thread server login");
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        if(loginDTO.isStatus()){
                            loginDTO.setMoviesList(CentralDatbase.getInstance().getMovies());
                            ClientInfo clientInfo = new ClientInfo(socketWrapper,loginDTO.getUserName());
                            clientList.add(clientInfo);
                            System.out.println("adding client" + " " + clientInfo.getProductionCompanyName() );
                        }
                        else {
                            loginDTO.setMoviesList(new ArrayList<Movies>());
                        }
                        socketWrapper.write(loginDTO);
                    }
                    else if(o instanceof SignupDTO){
                            SignupDTO signupDTO = (SignupDTO) o;
                            System.out.println("this is read server signup . we got :" + " " + signupDTO.getUserName() + " " + signupDTO.getPassword());
                            socketWrapper.write(server.addClient(signupDTO.getUserName(), signupDTO.getPassword()));
                    }else if(o instanceof soldPackage){
                        System.out.println("yo in read thread server sold package");
                        new Thread(() -> sell((soldPackage) o)).start();
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socketWrapper.closeConnection();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
