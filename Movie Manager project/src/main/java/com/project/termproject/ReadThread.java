package com.project.termproject;


import com.project.termproject.Data.LocalDatabase;
import com.project.termproject.Data.Movies;
import com.project.termproject.LoginUtil.LoginDTO;
import com.project.termproject.dto.UpdateRespond;
import com.project.termproject.dto.soldPackage;
import javafx.application.Platform;

public class ReadThread implements Runnable{
    private final Thread thread;
    private final MainApplication main;
    static MainApplication m;
    UpdateFromReadThread updateFromReadThread;

    public ReadThread(MainApplication main){
        this.main = main;
        this.m = main;
        this.thread = new Thread(this);
        thread.start();
    }
    public static void write(Object o){
        new Thread(() -> {
            try {

                if(o instanceof soldPackage){
                    soldPackage sp = (soldPackage) o;
                    System.out.println("yoyo in writeThread client" + " " + sp.getDestination());
                    m.getSocketWrapper().write(sp);
                }
                else {
                    System.out.println("obj maybe null or something else");
                }

            } catch (Exception e) {
                System.out.println("getting exp in writeThread client catch");
                e.printStackTrace();
            }
        }).start();
    }

    public void setUpdateFromReadThread(UpdateFromReadThread updateFromReadThread) {
        this.updateFromReadThread = updateFromReadThread;
    }

    @Override
    public void run() {
        try {
            while (true){
                Object o = main.getSocketWrapper().read();
                if (o != null){
                    if (o instanceof LoginDTO){
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        System.out.println("this is read thread");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(loginDTO.isStatus()){
                                    try {
                                        LocalDatabase.getInstance(loginDTO);
                                        main.showProductionCompanyPage(loginDTO);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }else {
                                    main.showAlert("Incorrect Credentials.","The username and password provided is not correct.");
                                }
                            }
                        });
                    }try {
                         if(o instanceof UpdateRespond){
                            UpdateRespond updateRespond = (UpdateRespond) o;
                            System.out.println("In read thread reading . The refresh read is " + updateRespond.refreshPCP);
                            for (Movies m : updateRespond.getMovies()){
                                System.out.println("RT " +m.getTitle() + " " + m.getProduction_comapny());
                            }
                            updateFromReadThread.updateFromServerRespond(updateRespond);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                main.getSocketWrapper().closeConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
