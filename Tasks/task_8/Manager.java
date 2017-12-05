package com.company;

public class Manager implements Runnable {
    Ship ship;
    Port port;

    public Manager(Ship ship, Port port){
        this.ship = ship;
        this.port = port;
    }
    @Override
    public void run() {
        System.out.println("Ship " + Thread.currentThread().getId() + " wants to dock.");
        //Waiting for dock
        while(!port.canDock()){
            Thread.yield();
        }
        System.out.println("Ship " + Thread.currentThread().getId() + " docked.");
        //Waiting for adding
        if(ship.isWantAdd()){
            int currentNeeds = ship.availablePlace();
            if(currentNeeds > 0){
                System.out.println("Ship " + Thread.currentThread().getId() + " wants to get " + currentNeeds + ".");
                while(!port.canGet(currentNeeds)){
                    Thread.yield();
                }
                System.out.println("Ship " + Thread.currentThread().getId() + " got " + currentNeeds + ".");
            }
        }
        //Waiting for removing
        if(ship.isWantRemove()){
            int curWeight = ship.getCurrentWeight();
            if(curWeight > 0){
                System.out.println("Ship " + Thread.currentThread().getId() + " wants to remove " + curWeight + ".");
                while(!port.canAdd(curWeight)){
                    Thread.yield();
                }
                System.out.println("Ship " + Thread.currentThread().getId() + " removed " + curWeight + ".");
            }
        }
        //Move from port
        port.finish();
        System.out.println("Ship " + Thread.currentThread().getId() + " moved from port.");
    }
}
