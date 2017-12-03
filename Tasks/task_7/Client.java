package com.company;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            String address = "127.0.0.1";
            InetAddress inetAddress = InetAddress.getByName(address);
            Socket socket = new Socket(inetAddress, 8080);
            Serial object = new Serial(120, "test object");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(object);
            oos.flush();
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
