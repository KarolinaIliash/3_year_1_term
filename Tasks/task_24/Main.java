package com.company;

public class Main {

    public static void main(String[] args) {
        MyCyclicBarrier cyclicBarrier=new MyCyclicBarrier(3);

        TestThread myRunnable1=new TestThread(cyclicBarrier);

        for(int i = 0; i < 3; i++){
            new Thread(myRunnable1).start();
            new Thread(myRunnable1).start();
            new Thread(myRunnable1).start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
