package com.company;

public class Ship {
    private int maxWeight;
    private int currentWeight;
    boolean wantAdd;
    boolean wantRemove;

    public Ship(int maxWeight, int currentWeight, boolean wantAdd, boolean wantRemove){
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.wantAdd = wantAdd;
        this.wantRemove = wantRemove;
    }

    void addWeight(int weight){
        currentWeight += weight;
    }

    boolean isWantAdd(){
        return wantAdd;
    }

    boolean isWantRemove(){
        return wantRemove;
    }

    int availablePlace(){
        return maxWeight - currentWeight;
    }

    int getCurrentWeight(){
        return currentWeight;
    }
}
