package com.company;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
    private int key;

    EncryptOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int smb) throws IOException{
        //Encrypt symbol
        smb ^= key;
        //Write it
        out.write(smb);
    }

    @Override
    public void write(byte[] symbols) throws IOException{
        this.write(symbols, 0, symbols.length);
    }

    @Override
    public void write(byte[] symbols, int off, int len) throws IOException, ArrayIndexOutOfBoundsException{
        byte[] smb = new byte[symbols.length];
        //Encrypt every symbol of array
        for(int i = off; i < off + len; i++){
            smb[i] = (byte) (key ^ symbols[i]);
        }
        //Write array
        out.write(smb, off, len);
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }
}
