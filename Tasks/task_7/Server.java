package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
	    Socket s = null;
	    try{
            ServerSocket ss = new ServerSocket(8080);
            s = ss.accept();
            System.out.println("Client here");
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Serial object = (Serial) ois.readObject();
            if(object.getFirst() != 120 || !object.getSecond().equals("test object")){
                System.err.println("Oops! It's not object which we're expected!");
                System.err.println(object.getFirst());
                System.err.println(object.getSecond());
            }
            else{
                System.out.println("It's ok.");
            }
            ois.close();
        } catch(IOException e){
	        e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
