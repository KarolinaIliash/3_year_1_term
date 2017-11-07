package com.company;

public enum PlaneEnum {
    PLANES("planes"),
    PLANE("plane"),
    CHARS("chars"),
    PARAMETERS("parameters"),
    ORIGIN("origin"),
    TYPE("type"),
    AMMUNITION("ammunition"),
    RADAR("radar"),
    PRICE("price"),
    HEIGHT("height"),
    LENGTH("length"),
    WINGSPAN("wingspan"),
    MODEL("model"),
    RACKETS("rackets"),
    SEATS("seats")
    ;
    private String value;
    private PlaneEnum(String value){
        this.value = value;
    }
    public String getValue(){return value;}
}