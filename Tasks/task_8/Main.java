package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    Port port = new Port(5, 350);
	    List<Thread> threads = new ArrayList(20);
        Random random = new Random();
        for(int i = 0; i < 20; i++){
            int maxWeight = 10 + random.nextInt(100);
            int currentWeight = random.nextInt(maxWeight);
            boolean wantAdd = random.nextBoolean();
            boolean wantRemove = true;
            Ship ship = new Ship(maxWeight, currentWeight, wantAdd, wantRemove);
            threads.add(new Thread(new Manager(ship, port)));
            threads.get(i).start();
        }
        //for(int i = 0; i < 20; i++){
          //  try {
            //    threads.get(i).join();
            //} catch (InterruptedException e) {
              //  e.printStackTrace();
            //}
        //}
    }
}
