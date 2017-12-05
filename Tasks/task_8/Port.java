package com.company;

public class Port {
    private int piersAmount;
    private int maxWeight;
    private int currentWeight = 0;
    private int shipsAmount = 0;

    public Port(int piersAmount, int maxWeight){
        this.piersAmount = piersAmount;
        this.maxWeight = maxWeight;
    }

    //Check if we can dock new ship, if yes - dock it
    public synchronized boolean canDock(){
        if(shipsAmount < piersAmount){
            shipsAmount++;
            System.out.println("ShipsAmount = " + shipsAmount);
            return true;
        }
        return false;
    }

    //Check if we can add needed weight, if yes - add it
    public synchronized boolean canAdd(int weight){
        if(maxWeight >= currentWeight + weight){
            currentWeight += weight;
            System.out.println("CurrentWeight = " + currentWeight);
            return true;
        }
        return false;
    }

    //Check if we can get needed weight, if yes - get it
    public synchronized boolean canGet(int weight){
        if(currentWeight >= weight){
            currentWeight -= weight;
            System.out.println("CurrentWeight = " + currentWeight);
            return true;
        }
        return false;
    }

    //Remove ship from port
    public synchronized void finish(){
        System.out.println("ShipsAmpunt = " + shipsAmount);
        shipsAmount--;
    }
}
