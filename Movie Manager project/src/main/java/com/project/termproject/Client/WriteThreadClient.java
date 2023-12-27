package com.project.termproject.Client;

import com.project.termproject.NetworkUtil.SocketWrapper;
import com.project.termproject.dto.soldPackage;

import java.io.IOException;

public class WriteThreadClient {
    public static SocketWrapper socketWrapper;
    public static void write(Object o){
        new Thread(() -> {
            try {
                if(o instanceof soldPackage){
                    soldPackage sp = (soldPackage) o;
                    System.out.println("yo in writeThread client" + " " + sp.getDestination());
                    socketWrapper.write(sp);
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

    public static void setSocketWrapper(SocketWrapper socketWrapper) {
        WriteThreadClient.socketWrapper = socketWrapper;
    }
}
