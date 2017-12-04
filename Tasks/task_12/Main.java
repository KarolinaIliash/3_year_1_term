package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    String fileName = "data.txt";
	    int key = 5;
	    try {
            EncryptOutputStream out = new EncryptOutputStream(new FileOutputStream(fileName), key);
            out.write(4);
            byte[] symbols = {3, 5, 6, 7, 8, 9};
            out.write(symbols);
            out.write(symbols, 1,2);
            DecryptInputStream in = new DecryptInputStream(new FileInputStream(fileName), key);
            int firstNumber = in.read();
            System.out.println(firstNumber);
            byte[] symbols_ = new byte[6];
            in.read(symbols_);
            System.out.println();
            for(int i = 0; i < symbols_.length; i++){
                System.out.print(symbols_[i] + " ");
            }
            System.out.println();
            in.read(symbols_, 1, 2);
            System.out.println();
            for(int i = 0; i < symbols_.length; i++){
                System.out.print(symbols_[i] + " ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
