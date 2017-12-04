package com.company;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream{
    private int key;

    public DecryptInputStream(InputStream in, int key){
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException{
        //Read symbol
        int smb = in.read();
        //Decrypt symbol before returning
        smb ^= key;
        return smb;
    }

    @Override
    public int read(byte[] symbols) throws IOException{
        return read(symbols, 0, symbols.length);
    }

    @Override
    public int read(byte[] symbols, int off, int len) throws IOException{
        //Read array of symbols
        int res= in.read(symbols, off, len);
        //If res == -1, we read nothing
        if(res != -1){
            //Decrypt array of symbols before returning
            for(int i = off; i < off + len; i++){
                symbols[i] ^= key;
            }
        }
        return res;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }
}
