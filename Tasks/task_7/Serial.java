package com.company;

import java.io.Serializable;

public class Serial implements Serializable{
    private int first;
    private String second;

    public Serial(int first, String second){
        this.first = first;
        this.second = second;
    }

    public int getFirst(){
        return first;
    }

    public String getSecond(){
        return second;
    }
}
