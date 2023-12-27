package com.project.termproject.NetworkUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWrapper {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

   public SocketWrapper(String ipAddress,int port)  {
        try {
            this.socket = new Socket(ipAddress,port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public SocketWrapper(Socket socket) {
        try {
            this.socket = socket;
            oos = new ObjectOutputStream(this.socket.getOutputStream());
            ois = new ObjectInputStream(this.socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Object read() {
       try{
           return ois.readUnshared();
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }
    public void write(Object obj)  {
       try {
           oos.writeUnshared(obj);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}
