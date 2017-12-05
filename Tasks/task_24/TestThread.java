package com.company;

public class TestThread implements Runnable {
    MyCyclicBarrier cb;

    public TestThread(MyCyclicBarrier cb){
        this.cb = cb;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() +
                " is waiting for all other threads to reach common barrier point");

        try {
            Thread.sleep(1000);
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("As all threads have reached common barrier point "
                + Thread.currentThread().getId() +
                " has been released");
    }
}
